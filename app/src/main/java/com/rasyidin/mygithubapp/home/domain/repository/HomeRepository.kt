package com.rasyidin.mygithubapp.home.domain.repository

import com.rasyidin.mygithubapp.core.domain.ResultState
import com.rasyidin.mygithubapp.home.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun getEvents(username: String): Flow<ResultState<List<Event>>>
}