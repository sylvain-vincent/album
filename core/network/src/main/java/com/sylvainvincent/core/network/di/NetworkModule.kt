package com.sylvainvincent.core.network.di

import com.sylvainvincent.core.network.retrofit.RetrofitTracksNetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

const val LEBONCOIN_STATIC_BASE_URL = "https://static.leboncoin.fr/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    fun provideRetrofit(networkJson: Json): Retrofit {
        return Retrofit.Builder()
            .baseUrl(LEBONCOIN_STATIC_BASE_URL)
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    fun provideTracksApi(retrofit: Retrofit): RetrofitTracksNetworkApi {
        return retrofit.create(RetrofitTracksNetworkApi::class.java)
    }
}