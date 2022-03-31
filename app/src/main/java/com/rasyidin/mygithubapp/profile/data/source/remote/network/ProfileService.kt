package com.rasyidin.mygithubapp.profile.data.source.remote.network

import com.rasyidin.mygithubapp.core.domain.ApiResponse
import com.rasyidin.mygithubapp.profile.data.source.remote.response.UserResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProfileService {

    @GET("user")
    suspend fun getAuthUser(): UserResponse

    @GET("users/{username}")
    suspend fun getDetailUser(
        @Path("username") username: String
    ): UserResponse

    @GET("user/followers")
    suspend fun getAuthUserFollowers(): List<UserResponse>

    @GET("user/following")
    suspend fun getAuthUserFollowing(): List<UserResponse>

    @PUT("user/following/{username}")
    suspend fun followUser(
        @Path("username") username: String
    ): ApiResponse

    @DELETE("user/following/{username}")
    suspend fun unfollowUser(
        @Path("username") username: String
    ): ApiResponse

    @GET("users/{username}/following")
    suspend fun getUserFollowing(
        @Path("username") username: String
    ): List<UserResponse>

    @GET("users/{username}/followers")
    suspend fun getUserFollowers(
        @Path("username") username: String
    ): List<UserResponse>

}