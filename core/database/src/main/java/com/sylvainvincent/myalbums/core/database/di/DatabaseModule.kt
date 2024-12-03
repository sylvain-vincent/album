package com.sylvainvincent.myalbums.core.database.di

import android.content.Context
import androidx.room.Room
import com.sylvainvincent.myalbums.core.database.MyAlbumsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val MY_ALBUMS_DATABASE_NAME = "my-album-database"

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideMyAlbumsDatabase(
        @ApplicationContext context: Context,
    ): MyAlbumsDatabase = Room.databaseBuilder(
        context = context,
        klass = MyAlbumsDatabase::class.java,
        name = MY_ALBUMS_DATABASE_NAME
    ).build()
}