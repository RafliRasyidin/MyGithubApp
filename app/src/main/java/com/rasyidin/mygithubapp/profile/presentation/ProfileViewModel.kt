package com.rasyidin.mygithubapp.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rasyidin.mygithubapp.core.domain.ApiResponse
import com.rasyidin.mygithubapp.core.domain.ResultState
import com.rasyidin.mygithubapp.core.utils.idle
import com.rasyidin.mygithubapp.profile.domain.model.User
import com.rasyidin.mygithubapp.profile.domain.usecase.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val useCase: ProfileUseCase) : ViewModel() {

    private var _user: MutableStateFlow<ResultState<User>> = idle()
    val user: StateFlow<ResultState<User>>
        get() = _user

    private var _userFollowers: MutableStateFlow<ResultState<List<User>>> = idle()
    val userFollowers: StateFlow<ResultState<List<User>>>
        get() = _userFollowers

    private var _userFollowing: MutableStateFlow<ResultState<List<User>>> = idle()
    val userFollowing: StateFlow<ResultState<List<User>>>
        get() = _userFollowing

    private var _eventFollow: MutableSharedFlow<ResultState<ApiResponse>> = idle()
    val eventFollow: SharedFlow<ResultState<ApiResponse>>
        get() = _eventFollow

    fun getDetailAuthUser() = viewModelScope.launch(Dispatchers.IO) {
        useCase.getAuthUser().collect { resultState ->
            _user.value = resultState
        }

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

    /*fun getAuthUser() = viewModelScope.launch(Dispatchers.IO) {
        useCase.getAuthUser().collect { resultState ->
            _user.value = resultState
        }
    }*/

    /*fun getAuthUserFollowers() = viewModelScope.launch(Dispatchers.IO) {
        useCase.getAuthUserFollowers().collect { resultState ->
            _userFollowers.value = resultState
        }
    }

    fun getAuthUserFollowing() = viewModelScope.launch(Dispatchers.IO) {
        useCase.getAuthUserFollowing().collect { resultState ->
            _userFollowing.value = resultState
        }
    }*/

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

    fun getDetailUser(username: String) = viewModelScope.launch(Dispatchers.IO) {
        useCase.getDetailUser(username).collect { resultState ->
            _user.value = resultState
        }

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

    /*un getUserFollowers(username: String) = viewModelScope.launch(Dispatchers.IO) {
        useCase.getUserFollowers(username).collect { resultState ->
            _userFollowers.value = resultState
        }
    }

    fun getUserFollowing(username: String) = viewModelScope.launch(Dispatchers.IO) {
        useCase.getUserFollowing(username).collect { resultState ->
            _userFollowing.value = resultState
        }
    }*/
}