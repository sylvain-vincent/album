package com.sylvainvincent.myalbums.core.domain.di

import com.sylvainvincent.myalbums.core.domain.GetTracksUseCaseImpl
import com.sylvainvincent.myalbums.core.domain.GetTracksUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TracksUseCaseModule {

    @Binds
    fun binds(implem : GetTracksUseCaseImpl) : GetTracksUseCase

}