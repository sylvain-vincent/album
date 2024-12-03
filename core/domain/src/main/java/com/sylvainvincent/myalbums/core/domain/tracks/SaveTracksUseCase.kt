package com.sylvainvincent.myalbums.core.domain.tracks

import com.sylvainvincent.myalbums.core.model.Track

interface SaveTracksUseCase {
    suspend operator fun invoke(trackList: List<Track>): Boolean
}