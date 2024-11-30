package com.sylvainvincent.myalbums.core.domain

import com.sylvainvincent.myalbums.core.data.repository.TracksRepository
import com.sylvainvincent.myalbums.core.model.Track
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTracksUseCaseImpl @Inject constructor(private val tracksRepository: TracksRepository) :
    GetTracksUseCase {

    override suspend fun invoke() : Flow<List<Track>> = tracksRepository.fetchTracks()
}