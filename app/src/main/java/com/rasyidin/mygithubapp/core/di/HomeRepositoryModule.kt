package com.rasyidin.mygithubapp.core.di

import com.rasyidin.mygithubapp.home.data.repository.HomeRepositoryImpl
import com.rasyidin.mygithubapp.home.data.source.remote.network.HomeService
import com.rasyidin.mygithubapp.home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeRepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(apiService: HomeService): HomeRepository =
        HomeRepositoryImpl(apiService)

}