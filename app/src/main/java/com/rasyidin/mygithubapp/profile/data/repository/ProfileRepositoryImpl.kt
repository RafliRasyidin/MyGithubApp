package com.rasyidin.mygithubapp.profile.data.repository

import com.rasyidin.mygithubapp.core.domain.ApiResponse
import com.rasyidin.mygithubapp.core.domain.ResultState
import com.rasyidin.mygithubapp.core.utils.fetch
import com.rasyidin.mygithubapp.core.utils.mapResult
import com.rasyidin.mygithubapp.home.domain.utils.toUser
import com.rasyidin.mygithubapp.profile.data.source.remote.ProfileRemoteDataSource
import com.rasyidin.mygithubapp.profile.domain.model.User
import com.rasyidin.mygithubapp.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val profileRemoteDataSource: ProfileRemoteDataSource) :
    ProfileRepository {

    override suspend fun getAuthUser(): Flow<ResultState<User>> {
        return fetch {
            profileRemoteDataSource.getAuthUser().toUser()
        }
    }

    override suspend fun getDetailUser(username: String): Flow<ResultState<User>> {
        return fetch {
            profileRemoteDataSource.getDetailUser(username).toUser()
        }
    }

    override suspend fun getAuthUserFollowers(): Flow<ResultState<List<User>>> {
        return fetch {
            profileRemoteDataSource.getAuthUserFollowers()
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
            profileRemoteDataSource.getAuthUserFollowing()
        }.map { resultState ->
            mapResult(resultState) {
                this?.map { userResponse ->
                    userResponse.toUser()
                }
            }
        }
    }

    override suspend fun followUser(username: String): Flow<ResultState<ApiResponse>> {
        return fetch {
            profileRemoteDataSource.followUser(username)
        }
    }

    override suspend fun unfollowUser(username: String): Flow<ResultState<ApiResponse>> {
        return fetch {
            profileRemoteDataSource.unfollowUser(username)
        }
    }

    override suspend fun getUserFollowing(username: String): Flow<ResultState<List<User>>> {
        return fetch {
            profileRemoteDataSource.getUserFollowing(username)
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
            profileRemoteDataSource.getUserFollowers(username)
        }.map { resultState ->
            mapResult(resultState) {
                this?.map { userResponse ->
                    userResponse.toUser()
                }
            }
        }
    }
}