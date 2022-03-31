package com.rasyidin.mygithubapp.profile.data.source.remote

import com.rasyidin.mygithubapp.core.domain.ApiResponse
import com.rasyidin.mygithubapp.profile.data.source.remote.response.UserResponse

interface ProfileRemoteDataSource {

    suspend fun getAuthUser(): UserResponse

    suspend fun getDetailUser(username: String): UserResponse

    suspend fun getAuthUserFollowers(): List<UserResponse>

    suspend fun getAuthUserFollowing(): List<UserResponse>

    suspend fun followUser(username: String): ApiResponse

    suspend fun unfollowUser(username: String): ApiResponse

    suspend fun getUserFollowing(username: String): List<UserResponse>

    suspend fun getUserFollowers(username: String): List<UserResponse>
}