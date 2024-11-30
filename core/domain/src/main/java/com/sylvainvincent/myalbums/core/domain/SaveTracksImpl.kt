package com.sylvainvincent.myalbums.core.domain

import com.sylvainvincent.myalbums.core.data.repository.TracksRepository
import com.sylvainvincent.myalbums.core.model.Track
import javax.inject.Inject

class SaveTracksImpl @Inject constructor(private val tracksRepository: TracksRepository) : SaveTracks {
    override suspend fun invoke(trackList: List<Track>) {
        tracksRepository.saveTracks(trackList)
    }
}