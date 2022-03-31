package com.rasyidin.mygithubapp.home.data.source.remote

import com.rasyidin.mygithubapp.home.data.source.remote.response.EventResponseItem
import com.rasyidin.mygithubapp.search.data.source.remote.response.RepositoryResponse

interface HomeRemoteDataSource {

    suspend fun getEvents(username: String): List<EventResponseItem>

    suspend fun getRepository(username: String, repoName: String): RepositoryResponse
}