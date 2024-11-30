package com.sylvainvincent.myalbums.core.domain

import com.sylvainvincent.myalbums.core.model.Track
import kotlinx.coroutines.flow.Flow

interface GetTracksUseCase {
    suspend fun invoke() : Flow<List<Track>>
}