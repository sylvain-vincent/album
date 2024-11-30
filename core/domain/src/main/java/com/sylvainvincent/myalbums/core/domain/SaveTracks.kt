package com.sylvainvincent.myalbums.core.domain

import com.sylvainvincent.myalbums.core.model.Track

interface SaveTracks {
    suspend fun invoke(trackList: List<Track>)
}