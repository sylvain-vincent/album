package com.sylvainvincent.myalbums.core.data.di

import com.sylvainvincent.myalbums.core.data.repository.TracksRepository
import com.sylvainvincent.myalbums.core.data.repository.TracksRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TracksModule {

    @Binds
    fun binds(implem: TracksRepositoryImpl): TracksRepository

}