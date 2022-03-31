package com.rasyidin.mygithubapp.home.data.source.remote.network

import com.rasyidin.mygithubapp.home.data.source.remote.response.EventResponseItem
import com.rasyidin.mygithubapp.search.data.source.remote.response.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {

    @GET("users/{username}/received_events")
    suspend fun getEvents(
        @Path("username") username: String
    ): List<EventResponseItem>

    @GET("repos/{owner}/{repo}")
    suspend fun getRepository(
        @Path("owner") username: String,
        @Path("repo") repoName: String
    ): RepositoryResponse
}