package com.rasyidin.mygithubapp.core.di

import com.rasyidin.mygithubapp.home.domain.repository.HomeRepository
import com.rasyidin.mygithubapp.home.domain.usecase.GetEvents
import com.rasyidin.mygithubapp.home.domain.usecase.HomeUseCase
import com.rasyidin.mygithubapp.profile.domain.repository.ProfileRepository
import com.rasyidin.mygithubapp.profile.domain.usecase.*
import com.rasyidin.mygithubapp.search.domain.repository.SearchRepository
import com.rasyidin.mygithubapp.search.domain.usecase.SearchRepositories
import com.rasyidin.mygithubapp.search.domain.usecase.SearchUseCase
import com.rasyidin.mygithubapp.search.domain.usecase.SearchUsers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun providesHomeUseCase(repository: HomeRepository): HomeUseCase {
        return HomeUseCase(getEvents = GetEvents(repository))
    }

    @Provides
    @Singleton
    fun providesProfileUseCase(repository: ProfileRepository): ProfileUseCase {
        return ProfileUseCase(
            getAuthUser = GetAuthUser(repository),
            getAuthUserFollowers = GetAuthUserFollowers(repository),
            getAuthUserFollowing = GetAuthUserFollowing(repository),
            getDetailUser = GetDetailUser(repository),
            getUserFollowers = GetUserFollowers(repository),
            getUserFollowing = GetUserFollowing(repository),
            followUser = FollowUser(repository),
            unfollowUser = UnfollowUser(repository),
            getAuthUserRepos = GetAuthUserRepos(repository),
            getUserRepos = GetUserRepos(repository)
        )
    }

    @Provides
    @Singleton
    fun providesSearchUseCase(repository: SearchRepository): SearchUseCase {
        return SearchUseCase(
            searchUsers = SearchUsers(repository),
            searchRepositories = SearchRepositories(repository)
        )
    }
}