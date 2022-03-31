package com.rasyidin.mygithubapp.search.data.repository

import com.rasyidin.mygithubapp.core.domain.ResultState
import com.rasyidin.mygithubapp.core.utils.fetch
import com.rasyidin.mygithubapp.core.utils.mapResult
import com.rasyidin.mygithubapp.home.domain.utils.toUser
import com.rasyidin.mygithubapp.profile.data.source.remote.ProfileRemoteDataSource
import com.rasyidin.mygithubapp.profile.domain.model.User
import com.rasyidin.mygithubapp.search.data.source.remote.SearchRemoteDataSource
import com.rasyidin.mygithubapp.search.domain.model.Repository
import com.rasyidin.mygithubapp.search.domain.repository.SearchRepository
import com.rasyidin.mygithubapp.search.domain.utils.toRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val profileRemoteDataSource: ProfileRemoteDataSource
) : SearchRepository {

    override suspend fun searchRepositories(search: String): Flow<ResultState<List<Repository?>>> {
        return fetch {
            searchRemoteDataSource.searchRepositories(search)
        }.map { resultState ->
            mapResult(resultState) {
                this?.repositoriesResponse?.map {
                    it?.toRepository()
                }
            }
        }
    }

    override suspend fun searchUsers(search: String): Flow<ResultState<List<User?>>> {
        return fetch {
            searchRemoteDataSource.searchUsers(search)
        }.map { resultState ->
            mapResult(resultState) {
                this?.users?.map { userResponse ->
                    val user = userResponse?.toUser()
                    profileRemoteDataSource.getDetailUser(user?.username.toString())
                }?.map { userResponse ->
                    userResponse.toUser()
                }
            }
        }
    }
}