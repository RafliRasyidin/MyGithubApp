package com.rasyidin.mygithubapp.search.domain.usecase

import com.rasyidin.mygithubapp.core.domain.ResultState
import com.rasyidin.mygithubapp.search.domain.model.Repository
import com.rasyidin.mygithubapp.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepositories(private val repository: SearchRepository) {

    suspend operator fun invoke(search: String): Flow<ResultState<List<Repository?>>> {
        return repository.searchRepositories(search)
    }
}