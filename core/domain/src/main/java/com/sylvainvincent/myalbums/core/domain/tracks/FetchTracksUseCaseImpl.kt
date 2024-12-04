package com.sylvainvincent.myalbums.core.domain.tracks

import com.sylvainvincent.myalbums.core.data.repository.TracksRepository
import com.sylvainvincent.myalbums.core.model.Track
import javax.inject.Inject

internal class FetchTracksUseCaseImpl @Inject constructor(
    private val tracksRepository: TracksRepository
) : FetchTracksUseCase {
    override suspend fun invoke(): Result<List<Track>> = tracksRepository.fetchTracks()
}