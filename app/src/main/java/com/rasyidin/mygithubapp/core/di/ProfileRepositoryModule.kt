package com.rasyidin.mygithubapp.core.di

import com.rasyidin.mygithubapp.profile.data.repository.ProfileRepositoryImpl
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
    fun provideRepository(apiService: ProfileService): ProfileRepository {
        return ProfileRepositoryImpl(apiService)
    }
}