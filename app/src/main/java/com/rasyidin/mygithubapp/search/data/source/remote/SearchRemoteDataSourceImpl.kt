package com.rasyidin.mygithubapp.search.data.source.remote

import com.rasyidin.mygithubapp.search.data.source.remote.network.SearchService
import com.rasyidin.mygithubapp.search.data.source.remote.response.SearchRepositoriesResponse
import com.rasyidin.mygithubapp.search.data.source.remote.response.SearchUsersResponse
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(private val apiService: SearchService): SearchRemoteDataSource {

    override suspend fun searchRepositories(search: String): SearchRepositoriesResponse {
        return apiService.searchRepositories(search)
    }

    override suspend fun searchUsers(search: String): SearchUsersResponse {
        return apiService.searchUsers(search)
    }
}