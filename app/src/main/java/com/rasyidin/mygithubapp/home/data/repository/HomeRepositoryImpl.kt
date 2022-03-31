package com.rasyidin.mygithubapp.home.data.repository

import com.rasyidin.mygithubapp.core.domain.ResultState
import com.rasyidin.mygithubapp.core.utils.fetch
import com.rasyidin.mygithubapp.core.utils.mapResult
import com.rasyidin.mygithubapp.home.data.source.remote.network.HomeService
import com.rasyidin.mygithubapp.home.domain.model.Event
import com.rasyidin.mygithubapp.home.domain.repository.HomeRepository
import com.rasyidin.mygithubapp.home.domain.utils.toEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val apiService: HomeService) :
    HomeRepository {

    override suspend fun getEvents(username: String): Flow<ResultState<List<Event>>> {
        return fetch {
            apiService.getEvents(username)
        }.map { result ->
            mapResult(result) {
                this?.map { eventResponseItem ->
                    val repoResponse = eventResponseItem.repoResponse
                    val repoName = repoResponse?.name?.substringAfter("/").toString()
                    val name = repoResponse?.name?.substringBefore("/").toString()
                    eventResponseItem.repoResponse = apiService.getRepository(name, repoName)
                    eventResponseItem.toEvent()
                }
            }
        }
    }

}