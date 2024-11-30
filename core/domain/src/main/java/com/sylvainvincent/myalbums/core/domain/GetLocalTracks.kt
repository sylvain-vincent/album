package com.sylvainvincent.myalbums.core.domain

import com.sylvainvincent.myalbums.core.model.Track
import kotlinx.coroutines.flow.Flow

interface GetLocalTracks {
    suspend fun invoke(): Flow<List<Track>>
}