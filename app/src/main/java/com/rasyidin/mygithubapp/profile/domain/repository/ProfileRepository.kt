package com.rasyidin.mygithubapp.profile.domain.repository

import com.rasyidin.mygithubapp.core.domain.ApiResponse
import com.rasyidin.mygithubapp.core.domain.ResultState
import com.rasyidin.mygithubapp.profile.domain.model.User
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getAuthUser(): Flow<ResultState<User>>

    suspend fun getDetailUser(username: String): Flow<ResultState<User>>

    suspend fun getAuthUserFollowers(): Flow<ResultState<List<User>>>

    suspend fun getAuthUserFollowing(): Flow<ResultState<List<User>>>

    suspend fun followUser(username: String): Flow<ResultState<ApiResponse>>

    suspend fun unfollowUser(username: String): Flow<ResultState<ApiResponse>>

    suspend fun getUserFollowing(username: String): Flow<ResultState<List<User>>>

    suspend fun getUserFollowers(username: String): Flow<ResultState<List<User>>>
}