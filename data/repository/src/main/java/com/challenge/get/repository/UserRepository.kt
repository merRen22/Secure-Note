package com.challenge.get.repository

import android.content.SharedPreferences
import com.challenge.get.model.User
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for [User]
 */
@Singleton
class UserRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) {
    /**
     * Deletes a [User] from the local db
     */
    fun deleteUser() = sharedPreferences.edit().clear().commit()
}
