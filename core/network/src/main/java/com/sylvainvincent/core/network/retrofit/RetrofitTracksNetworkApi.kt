package com.sylvainvincent.core.network.retrofit

import com.sylvainvincent.core.network.model.TrackResponse
import retrofit2.http.GET

interface RetrofitTracksNetworkApi {
    @GET("img/shared/technical-test.json")
    suspend fun fetchTracks() : List<TrackResponse>
}