package com.challenge.get.base

import android.content.Context
import android.widget.Toast
import timber.log.Timber
import javax.inject.Singleton

@Singleton
class AppErrorHandler(private val context: Context) : ErrorHandler {

    override fun handleError(error: Throwable, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        Timber.e(error)
    }
}