package com.rasyidin.mygithubapp.core.utils

import com.rasyidin.mygithubapp.core.domain.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

suspend fun <T : Any> fetch(call: suspend () -> T): Flow<ResultState<T>> = flow {
    emit(ResultState.Loading())
    emit(ResultState.Success(call.invoke()))
}.catch { error ->
    emit(ResultState.Error(error))
}.flowOn(Dispatchers.IO)

inline fun <T: Any, U: Any> mapResult(
    resultState: ResultState<out T>,
    mapper: T?.() -> U?
): ResultState<U> {
    return when (resultState) {
        is ResultState.Error -> ResultState.Error(resultState.throwable)
        is ResultState.Idle -> ResultState.Idle()
        is ResultState.Loading -> ResultState.Loading()
        is ResultState.Success -> {
            val data = resultState.data
            val mapData = mapper.invoke(data)
            ResultState.Success(mapData)
        }
    }
}

fun <T : Any> idle(): MutableStateFlow<ResultState<T>> = run {
    MutableStateFlow(ResultState.Idle())
}

fun <T : Any> ResultState<T>.onFailure(result: (Throwable) -> Unit) {
    if (this is ResultState.Error) {
        result.invoke(this.throwable)
    }
}

fun <T : Any> ResultState<T>.onSuccess(result: (T?) -> Unit) {
    if (this is ResultState.Success) {
        result.invoke(this.data)
    }
}

fun <T : Any> ResultState<T>.onLoading(result: () -> Unit) {
    if (this is ResultState.Loading) {
        result.invoke()
    }
}

fun <T : Any> ResultState<T>.onIdle(result: () -> Unit) {
    if (this is ResultState.Idle) {
        result.invoke()
    }
}