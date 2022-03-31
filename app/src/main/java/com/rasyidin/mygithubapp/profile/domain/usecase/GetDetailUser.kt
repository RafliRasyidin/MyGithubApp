package com.rasyidin.mygithubapp.profile.domain.usecase

import com.rasyidin.mygithubapp.core.domain.ResultState
import com.rasyidin.mygithubapp.profile.domain.model.User
import com.rasyidin.mygithubapp.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow

class GetDetailUser(private val profileRepository: ProfileRepository) {

    suspend operator fun invoke(username: String) : Flow<ResultState<User>> {
        return profileRepository.getDetailUser(username)
    }
}