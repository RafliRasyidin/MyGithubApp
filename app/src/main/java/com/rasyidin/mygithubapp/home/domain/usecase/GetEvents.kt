package com.rasyidin.mygithubapp.home.domain.usecase

import com.rasyidin.mygithubapp.core.domain.ResultState
import com.rasyidin.mygithubapp.home.domain.model.Event
import com.rasyidin.mygithubapp.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetEvents (private val homeRepository: HomeRepository){

    suspend operator fun invoke(username: String): Flow<ResultState<List<Event>>> {
        return homeRepository.getEvents(username)
    }
}