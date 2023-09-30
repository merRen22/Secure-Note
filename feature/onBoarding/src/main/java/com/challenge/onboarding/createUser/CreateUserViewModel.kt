package com.challenge.onboarding.createUser

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.challenge.get.base.AppConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateUserViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : ViewModel() {

    fun createUser(username: String, password: String): Boolean {
        return sharedPreferences.edit().putString(AppConstants.USER_NAME_PREFERENCE_KEY, username).commit() &&
            sharedPreferences.edit().putString(AppConstants.USER_PASSWORD_PREFERENCE_KEY, password).commit()
    }
}
