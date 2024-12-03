package com.sylvainvincent.myalbums.core.domain.di

import com.sylvainvincent.myalbums.core.domain.tracks.FetchTracksUseCaseImpl
import com.sylvainvincent.myalbums.core.domain.tracks.FetchTracksUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TracksUseCaseModule {

    @Binds
    fun bindsGetTracksUseCase(implem : FetchTracksUseCaseImpl) : FetchTracksUseCase
}