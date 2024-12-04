package com.sylvainvincent.myalbums.feature.tracks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvainvincent.myalbums.core.domain.tracks.FetchTracksUseCase
import com.sylvainvincent.myalbums.core.domain.tracks.HaveInternetConnectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TracksViewModel @Inject constructor(
    private val fetchTracksUseCase: FetchTracksUseCase,
    private val haveInternetConnectionUseCase: HaveInternetConnectionUseCase
) : ViewModel() {

    private val _trackState = MutableStateFlow<TracksState>(TracksState.Empty)
    val trackState = _trackState.asStateFlow()

    private val _event = Channel<Event>()
    val event = _event
        .receiveAsFlow()
        .stateIn(viewModelScope, SharingStarted.Lazily, Event.UNINITIALISED)

    init {
        fetchTracks()
    }

    fun fetchTracks() {
        viewModelScope.launch {
            _event.send(Event.LOADING)
            fetchTracksUseCase.invoke()
                .onSuccess { trackList ->
                    delay(timeMillis = 1000) // use a delay in order to avoid a rapid succession of 2 messages
                    _trackState.update { TracksState.Loaded(trackList) }
                    if(haveInternetConnectionUseCase()) {
                        _event.send(Event.FETCH_SUCCESSFUL)
                    } else {
                        _event.send(Event.NO_INTERNET)
                    }
                }.onFailure { _ ->
                    _trackState.update { TracksState.Error }
                    _event.send(Event.ERROR)
                }
        }
    }
}
