package com.rasyidin.mygithubapp.search.data.source.remote

import com.rasyidin.mygithubapp.search.data.source.remote.response.SearchRepositoriesResponse
import com.rasyidin.mygithubapp.search.data.source.remote.response.SearchUsersResponse

interface SearchRemoteDataSource {

    suspend fun searchRepositories(search: String): SearchRepositoriesResponse

    suspend fun searchUsers(search: String): SearchUsersResponse
}