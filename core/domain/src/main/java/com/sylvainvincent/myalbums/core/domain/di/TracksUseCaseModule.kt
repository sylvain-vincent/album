package com.sylvainvincent.myalbums.core.domain.di

import com.sylvainvincent.myalbums.core.domain.GetLocalTracks
import com.sylvainvincent.myalbums.core.domain.GetLocalTracksImpl
import com.sylvainvincent.myalbums.core.domain.GetTracksUseCaseImpl
import com.sylvainvincent.myalbums.core.domain.GetTracksUseCase
import com.sylvainvincent.myalbums.core.domain.SaveTracks
import com.sylvainvincent.myalbums.core.domain.SaveTracksImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TracksUseCaseModule {

    @Binds
    fun bindsGetTracksUseCase(implem : GetTracksUseCaseImpl) : GetTracksUseCase

    @Binds
    fun bindsGetLocalTracks(implem : GetLocalTracksImpl) : GetLocalTracks

    @Binds
    fun bindsSaveTracks(implem : SaveTracksImpl) : SaveTracks
}