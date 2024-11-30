package com.sylvainvincent.myalbums.core.domain

import com.sylvainvincent.myalbums.core.data.repository.TracksRepository
import com.sylvainvincent.myalbums.core.model.Track
import javax.inject.Inject

class GetTracksUseCaseImpl @Inject constructor(private val tracksRepository: TracksRepository) :
    GetTracksUseCase {

    override suspend fun invoke() : List<Track> = tracksRepository.fetchTracks()
}