package com.rasyidin.mygithubapp.profile.domain.usecase

import com.rasyidin.mygithubapp.core.domain.ResultState
import com.rasyidin.mygithubapp.profile.domain.model.User
import com.rasyidin.mygithubapp.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow

class GetAuthUserFollowers(private val repository: ProfileRepository) {

    suspend operator fun invoke(): Flow<ResultState<List<User>>> {
        return repository.getAuthUserFollowers()
    }
}