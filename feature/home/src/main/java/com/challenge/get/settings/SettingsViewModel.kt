package com.challenge.get.settings

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.challenge.get.base.AppConstants
import com.challenge.get.base.util.RequestState
import com.challenge.get.base.util.getCurrentDate
import com.challenge.get.model.Note
import com.challenge.get.repository.NoteRepository
import com.challenge.get.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val userRepository: UserRepository,
    private val noteRepository: NoteRepository,
) : ViewModel() {

    private val _mutableUserDeleted = MutableLiveData(false)
    val userDeleted: LiveData<Boolean> = _mutableUserDeleted

    val username = sharedPreferences.getString(AppConstants.USER_NAME_PREFERENCE_KEY, "")

    fun readJSONFile(reader: InputStreamReader) = liveData(viewModelScope.coroutineContext) {
        emit(RequestState.Loading)
        try {
            withContext(Dispatchers.IO) {
                val notes = readJSONData(reader)

                notes.forEach { note ->
                    if (note.creationDate.isNullOrEmpty()) note.creationDate = getCurrentDate()
                    noteRepository.addNote(note)
                }

                emit(RequestState.Success("Notes added"))
            }
        } catch (e: Exception) {
            emit(
                RequestState.Error("There was a problem reading the JSON file"),
            )
        }
    }

    private fun readJSONData(reader: InputStreamReader): List<Note> {
        val bufferedReader = BufferedReader(reader)
        val jsonString = bufferedReader.use { it.readText() }
        return Json.decodeFromString(jsonString)
    }

    /**
     * Delete all notes from user and user data
     */
    fun deleteNotes() = liveData(viewModelScope.coroutineContext) {
        emit(RequestState.Loading)
        try {
            noteRepository.deleteAllUserNotes()

            emit(RequestState.Success("All notes deleted"))
        } catch (e: Exception) {
            emit(
                RequestState.Error("There was a problem deleting your notes"),
            )
        }
    }

    fun deleteAccount() = liveData(viewModelScope.coroutineContext) {
        emit(RequestState.Loading)
        try {
            noteRepository.deleteAllUserNotes()
            userRepository.deleteUser()

            emit(RequestState.Success("Account deleted"))
        } catch (e: Exception) {
            emit(
                RequestState.Error("There was a problem deleting your account"),
            )
        }
    }
}
