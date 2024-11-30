package com.sylvainvincent.myalbums.core.data.repository

import com.sylvainvincent.myalbums.core.model.Track
import kotlinx.coroutines.flow.Flow

interface TracksRepository {
    suspend fun fetchTracks(): Flow<List<Track>>
    suspend fun saveTracks(trackList: List<Track>)
    suspend fun getLocalTracks(): Flow<List<Track>>
}