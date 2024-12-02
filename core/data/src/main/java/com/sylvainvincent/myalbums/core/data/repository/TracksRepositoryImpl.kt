package com.sylvainvincent.myalbums.core.data.repository

import com.sylvainvincent.myalbums.core.database.dao.TrackDao
import com.sylvainvincent.myalbums.core.database.model.TrackEntity
import com.sylvainvincent.myalbums.core.database.model.toTrack
import com.sylvainvincent.myalbums.core.database.model.toTrackEntity
import com.sylvainvincent.myalbums.core.model.Track
import com.sylvainvincent.myalbums.core.network.model.TrackResponse
import com.sylvainvincent.myalbums.core.network.model.toTrack
import com.sylvainvincent.myalbums.core.network.retrofit.RetrofitTracksNetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TracksRepositoryImpl @Inject constructor(
    private val tracksNetworkApi: RetrofitTracksNetworkApi,
    private val trackDao: TrackDao,
) : TracksRepository {
    override suspend fun fetchTracks(): Result<List<Track>> {
        return try {
            // todo, use retrofit response ?
            val result = tracksNetworkApi.fetchTracks()
            Result.success(result.map(TrackResponse::toTrack))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun saveTracks(trackList: List<Track>): Boolean {
        return trackDao.insertTracks(trackList.map(Track::toTrackEntity)).isNotEmpty()
    }

    override suspend fun getLocalTracks(): Flow<List<Track>> {
        return trackDao.getTracksEntity().map { it.map(TrackEntity::toTrack) }
    }
}