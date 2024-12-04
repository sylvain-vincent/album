package com.sylvainvincent.myalbums.feature.tracks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvainvincent.myalbums.core.domain.tracks.FetchTracksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
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
) : ViewModel() {

    private val _trackState = MutableStateFlow<TracksState>(TracksState.Empty)
    val trackState = _trackState.asStateFlow()

    private val _event = Channel<Event>()
    val event = _event
        .receiveAsFlow()
        .stateIn(viewModelScope, SharingStarted.Eagerly, Event.UNINITIALISED)

    init {
        fetchTracks()
    }

    fun fetchTracks() {
        viewModelScope.launch {
            _event.send(Event.LOADING)
            fetchTracksUseCase.invoke()
                .onSuccess { trackList ->
                    _trackState.update { TracksState.Loaded(trackList) }
                    _event.send(Event.FETCH_SUCCESSFUL)
                }.onFailure { _ ->
                    _trackState.update { TracksState.Error }
                    _event.send(Event.ERROR)
                }
        }
    }
}
