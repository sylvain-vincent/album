package com.sylvainvincent.myalbums.core.domain

import com.sylvainvincent.myalbums.core.model.Track

interface GetTracksUseCase {
    suspend fun invoke() : List<Track>
}