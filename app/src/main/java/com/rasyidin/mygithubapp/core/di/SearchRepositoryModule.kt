package com.rasyidin.mygithubapp.core.di

import com.rasyidin.mygithubapp.search.data.repository.SearchRepositoryImpl
import com.rasyidin.mygithubapp.search.data.source.remote.network.SearchService
import com.rasyidin.mygithubapp.search.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchRepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(searchService: SearchService): SearchRepository {
        return SearchRepositoryImpl(searchService)
    }
}