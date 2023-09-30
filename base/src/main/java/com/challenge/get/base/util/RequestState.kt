package com.challenge.get.base.util

sealed class RequestState<out T> {
    data class Success<out T>(val value: T) : RequestState<T>()
    data class Error(val errorMessage: String) : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()
}
