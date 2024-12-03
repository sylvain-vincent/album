package com.sylvainvincent.myalbums.feature.tracks

import com.sylvainvincent.myalbums.core.model.Track

sealed interface TracksState {
    data object Empty : TracksState
    data class Loaded(val trackList: List<Track>) : TracksState
}

enum class Event {
    UNINITIALISED,
    LOADING,
    ERROR,
    FETCH_SUCCESSFUL
}
