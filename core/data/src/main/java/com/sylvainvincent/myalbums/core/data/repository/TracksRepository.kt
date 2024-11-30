package com.sylvainvincent.myalbums.core.data.repository

import com.sylvainvincent.myalbums.core.model.Track

interface TracksRepository {
    suspend fun fetchTracks(): List<Track>
}