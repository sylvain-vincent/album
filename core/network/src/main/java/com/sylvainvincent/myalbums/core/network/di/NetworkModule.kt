package com.sylvainvincent.myalbums.core.network.di

import android.content.Context
import com.sylvainvincent.myalbums.core.network.NetworkConnectionInterceptor
import com.sylvainvincent.myalbums.core.network.model.TrackResponse
import com.sylvainvincent.myalbums.core.network.retrofit.RetrofitTracksNetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

const val LEBONCOIN_STATIC_BASE_URL = "https://static.leboncoin.fr/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }


    @Provides
    fun providesCreateOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(NetworkConnectionInterceptor(context))
            .build()
    }

    @Provides
    fun provideRetrofit(networkJson: Json, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(LEBONCOIN_STATIC_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Provides
    fun provideTracksApi(retrofit: Retrofit): RetrofitTracksNetworkApi {
        return retrofit.create(RetrofitTracksNetworkApi::class.java)
    }
}