package com.sylvainvincent.myalbums.core.domain

import com.sylvainvincent.myalbums.core.model.Track

interface FetchTracksUseCase {
    suspend fun invoke() : Result<List<Track>>
}