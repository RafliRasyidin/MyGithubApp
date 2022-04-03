package com.rasyidin.mygithubapp.profile.domain.usecase

import com.rasyidin.mygithubapp.core.domain.ResultState
import com.rasyidin.mygithubapp.profile.domain.repository.ProfileRepository
import com.rasyidin.mygithubapp.search.domain.model.Repository
import kotlinx.coroutines.flow.Flow

class GetUserRepos(private val repository: ProfileRepository) {

    suspend operator fun invoke(username: String): Flow<ResultState<List<Repository>>> {
        return repository.getUserRepos(username)
    }
}