package com.rasyidin.mygithubapp.profile.data.repository

import com.rasyidin.mygithubapp.core.domain.ResultState
import com.rasyidin.mygithubapp.core.utils.fetch
import com.rasyidin.mygithubapp.core.utils.mapResult
import com.rasyidin.mygithubapp.home.domain.utils.toUser
import com.rasyidin.mygithubapp.profile.data.source.remote.network.ProfileService
import com.rasyidin.mygithubapp.profile.domain.model.User
import com.rasyidin.mygithubapp.profile.domain.repository.ProfileRepository
import com.rasyidin.mygithubapp.search.domain.model.Repository
import com.rasyidin.mygithubapp.search.domain.utils.toRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val apiService: ProfileService) :
    ProfileRepository {

    override suspend fun getAuthUser(): Flow<ResultState<User>> {
        return fetch {
            apiService.getAuthUser().toUser()
        }
    }

    override suspend fun getDetailUser(username: String): Flow<ResultState<User>> {
        return fetch {
            apiService.getDetailUser(username).toUser()
        }
    }

    override suspend fun getAuthUserRepos(): Flow<ResultState<List<Repository>>> {
        return fetch {
            apiService.getAuthUserRepos()
        }.map {  resultState ->
            mapResult(resultState) {
                this?.map { repositoryResponse ->
                    repositoryResponse.toRepository()
                }
            }
        }
    }

    override suspend fun getAuthUserFollowers(): Flow<ResultState<List<User>>> {
        return fetch {
            apiService.getAuthUserFollowers()
        }.map { resultState ->
            mapResult(resultState) {
                this?.map { userResponse ->
                    userResponse.toUser()
                }
            }
        }
    }

    override suspend fun getAuthUserFollowing(): Flow<ResultState<List<User>>> {
        return fetch {
            apiService.getAuthUserFollowing()
        }.map { resultState ->
            mapResult(resultState) {
                this?.map { userResponse ->
                    userResponse.toUser()
                }
            }
        }
    }

    override suspend fun followUser(username: String): Flow<ResultState<Unit>> {
        return fetch {
            apiService.followUser(username)
        }
    }

    override suspend fun unfollowUser(username: String): Flow<ResultState<Unit>> {
        return fetch {
            apiService.unfollowUser(username)
        }
    }

    override suspend fun getUserRepos(username: String): Flow<ResultState<List<Repository>>> {
        return fetch {
            apiService.getUserRepositories(username)
        }.map { resultState ->
            mapResult(resultState) {
                this?.map { repositoryResponse ->
                    repositoryResponse.toRepository()
                }
            }
        }
    }

    override suspend fun getUserFollowing(username: String): Flow<ResultState<List<User>>> {
        return fetch {
            apiService.getUserFollowing(username)
        }.map { resultState ->
            mapResult(resultState) {
                this?.map { userResponse ->
                    userResponse.toUser()
                }
            }
        }
    }

    override suspend fun getUserFollowers(username: String): Flow<ResultState<List<User>>> {
        return fetch {
            apiService.getUserFollowers(username)
        }.map { resultState ->
            mapResult(resultState) {
                this?.map { userResponse ->
                    userResponse.toUser()
                }
            }
        }
    }

    override suspend fun isFollowed(username: String): Flow<ResultState<Unit>> {
        return fetch {
            apiService.isFollowed(username)
        }
    }
}