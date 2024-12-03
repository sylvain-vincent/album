package com.sylvainvincent.myalbums.core.domain.tracks

import com.sylvainvincent.myalbums.core.model.Track
import kotlinx.coroutines.flow.Flow

interface GetLocalTracksUseCase {
    suspend operator fun invoke(): Flow<List<Track>>
}