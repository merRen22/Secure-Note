package com.challenge.onboarding.login

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.challenge.get.base.AppConstants
import com.challenge.get.base.AppErrorHandler
import com.challenge.get.repository.di.MainDispatcher
import com.challenge.get.repository.util.RequestState
import com.challenge.get.repository.NoteRemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher

import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val noteRepository: NoteRemoteRepository,
    private val sharedPreferences: SharedPreferences,
    private val errorHandler: AppErrorHandler,
    @MainDispatcher private val coroutineDispatcher: CoroutineDispatcher,
) : ViewModel() {

    val username = sharedPreferences.getString(AppConstants.USER_NAME_PREFERENCE_KEY, "")

    fun login(password: String): Boolean {
        val userPassword = sharedPreferences.getString(AppConstants.USER_PASSWORD_PREFERENCE_KEY, "")

        return password == userPassword
    }

    fun getFromService() = liveData(viewModelScope.coroutineContext + coroutineDispatcher) {
        emit(RequestState.Loading)
        try {
            val response = noteRepository.getNotesFromService()

            for (note in response) {
                println(note.id)
            }

            emit(RequestState.Success(response))
        } catch (e: Exception) {
            errorHandler.handleError(e, "There was a problem")
            emit(RequestState.Error())
        }
    }
}
