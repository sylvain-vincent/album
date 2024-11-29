package com.sylvainvincent.myalbums.core.domain

import com.sylvainvincent.myalbums.core.data.repository.TracksRepository
import javax.inject.Inject

class GetTracksUseCaseImpl @Inject constructor(private val tracksRepository: TracksRepository) :
    GetTracksUseCase {

    override fun invoke() {
        tracksRepository.fetchTracks()
    }
}