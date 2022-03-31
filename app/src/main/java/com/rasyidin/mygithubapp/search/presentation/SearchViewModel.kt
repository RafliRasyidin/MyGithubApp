package com.rasyidin.mygithubapp.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rasyidin.mygithubapp.core.domain.ResultState
import com.rasyidin.mygithubapp.core.utils.idle
import com.rasyidin.mygithubapp.profile.domain.model.User
import com.rasyidin.mygithubapp.search.domain.model.Repository
import com.rasyidin.mygithubapp.search.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCase: SearchUseCase) : ViewModel() {

    private var _repositories: MutableStateFlow<ResultState<List<Repository?>>> = idle()
    val repositories: StateFlow<ResultState<List<Repository?>>> = _repositories

    private var _users: MutableStateFlow<ResultState<List<User?>>> = idle()
    val users: StateFlow<ResultState<List<User?>>> = _users

    fun searchRepositories(search: String) = viewModelScope.launch(Dispatchers.IO) {
        useCase.searchRepositories(search).collect { resultState ->
            _repositories.value = resultState
        }
    }

    fun searchUsers(search: String) = viewModelScope.launch(Dispatchers.IO) {
        useCase.searchUsers(search).collect { resultState ->
            _users.value = resultState
        }
    }
}