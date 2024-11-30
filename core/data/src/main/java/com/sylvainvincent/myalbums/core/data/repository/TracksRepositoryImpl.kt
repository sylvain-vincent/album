package com.sylvainvincent.myalbums.core.data.repository

import com.sylvainvincent.myalbums.core.model.Track
import com.sylvainvincent.myalbums.core.network.model.TrackResponse
import com.sylvainvincent.myalbums.core.network.model.toTrack
import com.sylvainvincent.myalbums.core.network.retrofit.RetrofitTracksNetworkApi
import javax.inject.Inject

class TracksRepositoryImpl @Inject constructor(private val tracksNetworkApi : RetrofitTracksNetworkApi) : TracksRepository {
    override suspend fun fetchTracks() : List<Track>{
        return tracksNetworkApi.fetchTracks().map(TrackResponse::toTrack)
    }
}