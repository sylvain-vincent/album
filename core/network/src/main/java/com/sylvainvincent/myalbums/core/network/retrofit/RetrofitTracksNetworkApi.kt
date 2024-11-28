package com.sylvainvincent.myalbums.core.network.retrofit

import com.sylvainvincent.myalbums.core.network.model.TrackResponse
import retrofit2.http.GET

interface RetrofitTracksNetworkApi {
    @GET("img/shared/technical-test.json")
    suspend fun fetchTracks() : List<TrackResponse>
}