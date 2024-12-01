package com.sylvainvincent.myalbums.core.domain

import com.sylvainvincent.myalbums.core.model.Track
import kotlinx.coroutines.flow.Flow

interface FetchTracksUseCase {
    suspend fun invoke() : Flow<List<Track>>
}