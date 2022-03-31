package com.rasyidin.mygithubapp.search.domain.usecase

import com.rasyidin.mygithubapp.core.domain.ResultState
import com.rasyidin.mygithubapp.profile.domain.model.User
import com.rasyidin.mygithubapp.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchUsers(private val repository: SearchRepository) {

    suspend operator fun invoke(search: String): Flow<ResultState<List<User?>>> {
        return repository.searchUsers(search)
    }
}