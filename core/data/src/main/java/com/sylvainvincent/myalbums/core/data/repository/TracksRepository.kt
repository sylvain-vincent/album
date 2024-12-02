package com.sylvainvincent.myalbums.core.data.repository

import com.sylvainvincent.myalbums.core.model.Track
import kotlinx.coroutines.flow.Flow

interface TracksRepository {
    suspend fun fetchTracks(): Result<List<Track>>
    suspend fun saveTracks(trackList: List<Track>): Boolean
    suspend fun getLocalTracks(): Flow<List<Track>>
}