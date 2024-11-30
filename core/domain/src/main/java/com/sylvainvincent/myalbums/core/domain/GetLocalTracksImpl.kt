package com.sylvainvincent.myalbums.core.domain

import com.sylvainvincent.myalbums.core.data.repository.TracksRepository
import com.sylvainvincent.myalbums.core.model.Track
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocalTracksImpl @Inject constructor(private val tracksRepository: TracksRepository) : GetLocalTracks {
    override suspend fun invoke(): Flow<List<Track>> {
        return tracksRepository.getLocalTracks()
    }
}