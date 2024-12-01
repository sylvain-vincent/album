package com.sylvainvincent.myalbums.core.domain.di

import com.sylvainvincent.myalbums.core.domain.GetLocalTracksUseCase
import com.sylvainvincent.myalbums.core.domain.GetLocalTracksUseCaseImpl
import com.sylvainvincent.myalbums.core.domain.FetchTracksUseCaseImpl
import com.sylvainvincent.myalbums.core.domain.FetchTracksUseCase
import com.sylvainvincent.myalbums.core.domain.SaveTracksUseCase
import com.sylvainvincent.myalbums.core.domain.SaveTracksUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TracksUseCaseModule {

    @Binds
    fun bindsGetTracksUseCase(implem : FetchTracksUseCaseImpl) : FetchTracksUseCase

    @Binds
    fun bindsGetLocalTracks(implem : GetLocalTracksUseCaseImpl) : GetLocalTracksUseCase

    @Binds
    fun bindsSaveTracks(implem : SaveTracksUseCaseImpl) : SaveTracksUseCase
}