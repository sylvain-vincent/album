package com.sylvainvincent.myalbums.feature.tracks

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvainvincent.myalbums.core.domain.tracks.FetchTracksUseCase
import com.sylvainvincent.myalbums.core.model.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TracksViewModel @Inject constructor(
    private val fetchTracksUseCase: FetchTracksUseCase,
) : ViewModel() {

    private val _trackState = MutableStateFlow<TracksState>(TracksState.Empty)
    val trackState = _trackState.asStateFlow()

    init {
        fetchTracks()
    }

    fun fetchTracks() {
        viewModelScope.launch {
            _trackState.emit(TracksState.Loading)
            delay(4000)
            fetchTracksUseCase.invoke()
                .onSuccess { trackList ->
                    _trackState.emit(TracksState.Loaded(trackList))
                }.onFailure { _ ->
                    _trackState.emit(TracksState.Error)
                }
        }
    }
}

sealed class TracksState {
    data object Empty : TracksState()
    data object Loading : TracksState()
    data class Loaded(val trackList: List<Track>) : TracksState()
    data object Error : TracksState()
}
