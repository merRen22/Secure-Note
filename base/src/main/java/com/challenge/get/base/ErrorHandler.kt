package com.challenge.get.base

interface ErrorHandler {

    fun handleError(error: Throwable, message: String)
}