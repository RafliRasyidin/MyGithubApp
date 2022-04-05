package com.rasyidin.mygithubapp.profile.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rasyidin.mygithubapp.core.domain.ResultState
import com.rasyidin.mygithubapp.core.utils.idle
import com.rasyidin.mygithubapp.profile.domain.model.User
import com.rasyidin.mygithubapp.profile.domain.usecase.ProfileUseCase
import com.rasyidin.mygithubapp.search.domain.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val useCase: ProfileUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _user: MutableStateFlow<ResultState<User>> = idle()
    val user: StateFlow<ResultState<User>>
        get() = _user

    private val _userRepos: MutableStateFlow<ResultState<List<Repository>>> = idle()
    val userRepos: StateFlow<ResultState<List<Repository>>>
        get() = _userRepos

    private val _userFollowers: MutableStateFlow<ResultState<List<User>>> = idle()
    val userFollowers: StateFlow<ResultState<List<User>>>
        get() = _userFollowers

    private val _userFollowing: MutableStateFlow<ResultState<List<User>>> = idle()
    val userFollowing: StateFlow<ResultState<List<User>>>
        get() = _userFollowing

    private val _eventFollow: MutableSharedFlow<ResultState<Unit>> = idle()
    val eventFollow: SharedFlow<ResultState<Unit>>
        get() = _eventFollow

    private val _isFollowed: MutableStateFlow<ResultState<Unit>> = idle()
    val isFollowed: StateFlow<ResultState<Unit>>
        get() = _isFollowed

    init {
        val username: String? = savedStateHandle["username"]

        if (username.isNullOrEmpty()) {
            getDetailAuthUser()
        } else {
            getDetailUser(username)
        }
    }

    fun followUser(username: String) = viewModelScope.launch(Dispatchers.IO) {
        useCase.followUser(username).collect { resultState ->
            _eventFollow.emit(resultState)
        }
    }

    fun unfollowUser(username: String) = viewModelScope.launch(Dispatchers.IO) {
        useCase.unfollowUser(username).collect { resultState ->
            _eventFollow.emit(resultState)
        }
    }

    fun getAuthReposUser() = viewModelScope.launch(Dispatchers.IO) {
        useCase.getAuthUserRepos().collect { resultState ->
            _userRepos.value = resultState
        }
    }

    fun getAuthFollUser() = viewModelScope.launch(Dispatchers.IO) {
        val dataUserFollowers = async {
            useCase.getAuthUserFollowers()
        }

        val dataUserFollowing = async {
            useCase.getAuthUserFollowing()
        }

        dataUserFollowers.await().collect { resultState ->
            _userFollowers.value = resultState
        }

        dataUserFollowing.await().collect { resultState ->
            _userFollowing.value = resultState
        }

    }

    fun getDetailAuthUser() = viewModelScope.launch(Dispatchers.IO) {
        useCase.getAuthUser().collect { resultState ->
            _user.value = resultState
        }

    }

    fun getDetailUser(username: String) = viewModelScope.launch(Dispatchers.IO) {
        useCase.getDetailUser(username).collect { resultState ->
            _user.value = resultState
        }

        val isFollowed = async {
            useCase.isFollowed(username)
        }

        isFollowed.await().collect { resultState ->
            _isFollowed.value = resultState
        }

    }

    fun getReposUser(username: String) = viewModelScope.launch(Dispatchers.IO) {
        useCase.getUserRepos(username).collect { resultState ->
            _userRepos.value = resultState
        }
    }

    fun getFollUser(username: String) = viewModelScope.launch(Dispatchers.IO) {
        val dataUserFollowers = async {
            useCase.getUserFollowers(username)
        }

        val dataUserFollowing = async {
            useCase.getUserFollowing(username)
        }

        dataUserFollowers.await().collect { resultState ->
            _userFollowers.value = resultState
        }

        dataUserFollowing.await().collect { resultState ->
            _userFollowing.value = resultState
        }
    }
}