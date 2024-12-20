package com.sylvainvincent.myalbums.core.data.repository

import com.sylvainvincent.myalbums.core.common.IoDispatcher
import com.sylvainvincent.myalbums.core.database.dao.TrackDao
import com.sylvainvincent.myalbums.core.database.model.TrackEntity
import com.sylvainvincent.myalbums.core.database.model.toTrack
import com.sylvainvincent.myalbums.core.database.model.toTrackEntity
import com.sylvainvincent.myalbums.core.model.Track
import com.sylvainvincent.myalbums.core.network.exception.NoNetworkException
import com.sylvainvincent.myalbums.core.network.model.TrackResponse
import com.sylvainvincent.myalbums.core.network.model.toTrack
import com.sylvainvincent.myalbums.core.network.retrofit.RetrofitTracksNetworkApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class TracksRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val tracksNetworkApi: RetrofitTracksNetworkApi,
    private val trackDao: TrackDao,
) : TracksRepository {

    override suspend fun fetchTracks(): Result<List<Track>> {
        return try {
            val result = tracksNetworkApi.fetchTracks()
            val trackList = result.map(TrackResponse::toTrack)
            withContext(ioDispatcher) {
                trackDao.insertTracks(trackList.map(Track::toTrackEntity))
            }
            Result.success(trackList)
        } catch (e: Exception) {
            if(e is NoNetworkException) {
                val localTrackList = withContext(ioDispatcher) {
                    trackDao.getTracksEntity()
                }
                Result.success(localTrackList.map(TrackEntity::toTrack))
            } else {
                Result.failure(e)
            }
        }
    }
}