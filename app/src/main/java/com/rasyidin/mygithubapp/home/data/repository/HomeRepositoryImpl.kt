package com.rasyidin.mygithubapp.home.data.repository

import com.rasyidin.mygithubapp.core.domain.ResultState
import com.rasyidin.mygithubapp.core.utils.fetch
import com.rasyidin.mygithubapp.core.utils.mapResult
import com.rasyidin.mygithubapp.home.data.source.remote.HomeRemoteDataSource
import com.rasyidin.mygithubapp.home.domain.model.Event
import com.rasyidin.mygithubapp.home.domain.repository.HomeRepository
import com.rasyidin.mygithubapp.home.domain.utils.toEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeRemoteDataSource: HomeRemoteDataSource) :
    HomeRepository {

    override suspend fun getEvents(username: String): Flow<ResultState<List<Event>>> {
        return fetch {
            homeRemoteDataSource.getEvents(username)
        }.map { result ->
            mapResult(result) {
                this?.map { eventResponseItem ->
                    val event = eventResponseItem
                    val repoName = event.repoResponse?.name?.substringAfter("/").toString()
                    val name = event.repoResponse?.name?.substringBefore("/").toString()
                    eventResponseItem.repoResponse = homeRemoteDataSource.getRepository(name, repoName)
                    eventResponseItem.toEvent()
                }
            }
        }
    }

}