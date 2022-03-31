package com.rasyidin.mygithubapp.core.di

import com.rasyidin.mygithubapp.profile.data.repository.ProfileRepositoryImpl
import com.rasyidin.mygithubapp.profile.data.source.remote.ProfileRemoteDataSource
import com.rasyidin.mygithubapp.profile.data.source.remote.ProfileRemoteDataSourceImpl
import com.rasyidin.mygithubapp.profile.data.source.remote.network.ProfileService
import com.rasyidin.mygithubapp.profile.domain.repository.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProfileRepositoryModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ProfileService): ProfileRemoteDataSource {
        return ProfileRemoteDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideRepository(profileRemoteDataSource: ProfileRemoteDataSource): ProfileRepository {
        return ProfileRepositoryImpl(profileRemoteDataSource)
    }
}