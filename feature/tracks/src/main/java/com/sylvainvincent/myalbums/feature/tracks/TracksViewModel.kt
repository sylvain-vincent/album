package com.sylvainvincent.myalbums.feature.tracks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sylvainvincent.myalbums.core.domain.GetLocalTracksUseCase
import com.sylvainvincent.myalbums.core.domain.FetchTracksUseCase
import com.sylvainvincent.myalbums.core.domain.SaveTracksUseCase
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
    private val saveTracksUseCase: SaveTracksUseCase,
    getLocalTracksUseCase: GetLocalTracksUseCase,
) : ViewModel() {

    private val _trackState = MutableStateFlow<TracksState>(TracksState.Empty)
    val trackState = _trackState.asStateFlow()

    private val _trackNetworkState = MutableStateFlow<NetworkTracksState>(NetworkTracksState.Empty)
    val trackNetworkState = _trackNetworkState.asStateFlow()

    init {
        viewModelScope.launch {
            _trackState.emit(TracksState.Loading)
            delay(4000)
            getLocalTracksUseCase.invoke().collect { trackList ->
                _trackState.emit(TracksState.Loaded(trackList))
            }
        }

        viewModelScope.launch {
            fetchTracks()
        }
    }

    suspend fun fetchTracks() {
        _trackNetworkState.emit(NetworkTracksState.Loading)
        delay(4000)
        fetchTracksUseCase.invoke().collect { trackList ->
            val successfullySaved = saveTracksUseCase(trackList = trackList)
            if(successfullySaved) _trackNetworkState.emit(NetworkTracksState.Success)
            else _trackNetworkState.emit(NetworkTracksState.Error)
        }
    }
}

sealed class NetworkTracksState {
    data object Empty : NetworkTracksState()
    data object Loading : NetworkTracksState()
    data object Success : NetworkTracksState()
    data object Error : NetworkTracksState()
}

sealed class TracksState {
    data object Empty : TracksState()
    data object Loading : TracksState()
    data class Loaded(val trackList: List<Track>) : TracksState()
    data object Error : TracksState()
}

