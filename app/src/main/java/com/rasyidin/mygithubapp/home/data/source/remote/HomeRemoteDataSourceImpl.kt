package com.rasyidin.mygithubapp.home.data.source.remote

import com.rasyidin.mygithubapp.home.data.source.remote.network.HomeService
import com.rasyidin.mygithubapp.home.data.source.remote.response.EventResponseItem
import com.rasyidin.mygithubapp.search.data.source.remote.response.RepositoryResponse
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(private val apiService: HomeService) :
    HomeRemoteDataSource {

    override suspend fun getEvents(username: String): List<EventResponseItem> {
        return apiService.getEvents(username)
    }

    override suspend fun getRepository(username: String, repoName: String): RepositoryResponse {
        return apiService.getRepository(username, repoName)
    }
}