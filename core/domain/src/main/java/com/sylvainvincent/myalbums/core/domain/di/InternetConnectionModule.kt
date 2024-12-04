package com.sylvainvincent.myalbums.core.domain.di

import com.sylvainvincent.myalbums.core.domain.tracks.HaveInternetConnectionUseCase
import com.sylvainvincent.myalbums.core.domain.tracks.HaveInternetConnectionUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface InternetConnectionModule {

    @Binds
    fun binds(implem : HaveInternetConnectionUseCaseImpl) : HaveInternetConnectionUseCase

}