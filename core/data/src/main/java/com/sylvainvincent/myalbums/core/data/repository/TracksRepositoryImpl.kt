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
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TracksRepositoryImpl @Inject constructor(
    private val tracksNetworkApi: RetrofitTracksNetworkApi,
    private val trackDao: TrackDao,
) : TracksRepository {
    override suspend fun fetchTracks(): Flow<List<Track>> {
        return flow { emit(tracksNetworkApi.fetchTracks().map(TrackResponse::toTrack)) }
    }

    override suspend fun saveTracks(trackList: List<Track>) {
        trackDao.upsertTracks(trackList.map(Track::toTrackEntity))
    }

    override suspend fun getLocalTracks(): Flow<List<Track>> {
        return trackDao.getTracksEntity().map { it.map(TrackEntity::toTrack) }
    }
}