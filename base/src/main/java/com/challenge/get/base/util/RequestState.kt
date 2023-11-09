package com.challenge.get.base.util

sealed class RequestState<out T> {
    data class Success<out T>(val value: T) : RequestState<T>()
    data class Error(val errorMessage: String? = null) : RequestState<Nothing>()
    data object Loading : RequestState<Nothing>()
}
