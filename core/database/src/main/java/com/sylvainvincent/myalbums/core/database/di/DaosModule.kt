package com.sylvainvincent.myalbums.core.database.di

import com.sylvainvincent.myalbums.core.database.MyAlbumsDatabase
import com.sylvainvincent.myalbums.core.database.dao.TrackDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {

    @Provides
    @Singleton
    fun provideTrackDao(
        database: MyAlbumsDatabase,
    ): TrackDao = database.trackDao()
}