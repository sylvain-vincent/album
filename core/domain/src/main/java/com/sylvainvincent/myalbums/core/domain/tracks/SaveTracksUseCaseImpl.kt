package com.sylvainvincent.myalbums.core.domain.tracks

import com.sylvainvincent.myalbums.core.data.repository.TracksRepository
import com.sylvainvincent.myalbums.core.model.Track
import javax.inject.Inject

class SaveTracksUseCaseImpl @Inject constructor(private val tracksRepository: TracksRepository) :
    SaveTracksUseCase {
    override suspend fun invoke(trackList: List<Track>) = tracksRepository.saveTracks(trackList)
}