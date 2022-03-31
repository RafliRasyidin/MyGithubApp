package com.rasyidin.mygithubapp.search.domain.repository

import com.rasyidin.mygithubapp.core.domain.ResultState
import com.rasyidin.mygithubapp.profile.domain.model.User
import com.rasyidin.mygithubapp.search.domain.model.Repository
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun searchRepositories(search: String): Flow<ResultState<List<Repository?>>>

    suspend fun searchUsers(search: String): Flow<ResultState<List<User?>>>
}