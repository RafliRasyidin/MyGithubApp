package com.rasyidin.mygithubapp.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rasyidin.mygithubapp.core.domain.ResultState
import com.rasyidin.mygithubapp.core.utils.idle
import com.rasyidin.mygithubapp.home.domain.model.Event
import com.rasyidin.mygithubapp.home.domain.usecase.HomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeUseCase: HomeUseCase) : ViewModel() {

    private val _events: MutableStateFlow<ResultState<List<Event>>> = idle()
    val events: StateFlow<ResultState<List<Event>>> = _events

    init {
        getEvents()
    }

    fun getEvents() = viewModelScope.launch(Dispatchers.IO) {
        homeUseCase.getEvents("RafliRasyidin").collect { resultState ->
            _events.value = resultState
        }
    }
}