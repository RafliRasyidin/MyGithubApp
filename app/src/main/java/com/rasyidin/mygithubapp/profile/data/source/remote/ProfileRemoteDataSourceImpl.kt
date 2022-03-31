package com.rasyidin.mygithubapp.profile.data.source.remote

import com.rasyidin.mygithubapp.core.domain.ApiResponse
import com.rasyidin.mygithubapp.profile.data.source.remote.network.ProfileService
import com.rasyidin.mygithubapp.profile.data.source.remote.response.UserResponse
import javax.inject.Inject

class ProfileRemoteDataSourceImpl @Inject constructor(private val apiService: ProfileService) : ProfileRemoteDataSource {

    override suspend fun getAuthUser(): UserResponse {
        return apiService.getAuthUser()
    }

    override suspend fun getDetailUser(username: String): UserResponse {
        return apiService.getDetailUser(username)
    }

    override suspend fun getAuthUserFollowers(): List<UserResponse> {
        return apiService.getAuthUserFollowers()
    }

    override suspend fun getAuthUserFollowing(): List<UserResponse> {
        return apiService.getAuthUserFollowing()
    }

    override suspend fun followUser(username: String): ApiResponse {
        return apiService.followUser(username)
    }

    override suspend fun unfollowUser(username: String): ApiResponse {
        return apiService.unfollowUser(username)
    }

    override suspend fun getUserFollowing(username: String): List<UserResponse> {
        return apiService.getUserFollowing(username)
    }

    override suspend fun getUserFollowers(username: String): List<UserResponse> {
        return apiService.getUserFollowers(username)
    }
}