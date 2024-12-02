package com.sylvainvincent.myalbums.core.domain

import com.sylvainvincent.myalbums.core.data.repository.TracksRepository
import com.sylvainvincent.myalbums.core.model.Track
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchTracksUseCaseImpl @Inject constructor(private val tracksRepository: TracksRepository) :
    FetchTracksUseCase {

    override suspend fun invoke() : Result<List<Track>> = tracksRepository.fetchTracks()
}