package com.challenge.onboarding.login

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.challenge.get.base.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {

    val username = sharedPreferences.getString(AppConstants.USER_NAME_PREFERENCE_KEY, "")

    fun login(password: String): Boolean {
        val userPassword = sharedPreferences.getString(AppConstants.USER_PASSWORD_PREFERENCE_KEY, "")

        return password == userPassword
    }
}
