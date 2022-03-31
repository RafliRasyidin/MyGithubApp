package com.rasyidin.mygithubapp.search.data.source.remote.network

import com.rasyidin.mygithubapp.search.data.source.remote.response.SearchRepositoriesResponse
import com.rasyidin.mygithubapp.search.data.source.remote.response.SearchUsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") search: String
    ): SearchRepositoriesResponse

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") search: String
    ): SearchUsersResponse
}