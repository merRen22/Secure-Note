package com.challenge.get.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.challenge.get.base.AppErrorHandler
import com.challenge.get.base.util.RequestState
import com.challenge.get.base.util.getCurrentDate
import com.challenge.get.model.Note
import com.challenge.get.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * [ViewModel] to store and manage detail of [Note].
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val errorHandler: AppErrorHandler,
) : ViewModel() {

    private val _serviceIsLoading = MutableLiveData(false)
    val serviceIsLoading: LiveData<Boolean> = _serviceIsLoading

    var noteid = 0
    var title = mutableStateOf("")
    var description = mutableStateOf("")
    var image = mutableStateOf("")
    var createdDate = ""

    fun findNote(id: Int) = liveData(viewModelScope.coroutineContext) {
        noteid = id
        _serviceIsLoading.value = true
        emit(RequestState.Loading)
        try {
            val response = noteRepository.findNoteById(id)
            _serviceIsLoading.value = false
            createdDate = response.creationDate ?: ""
            emit(RequestState.Success(response))
        } catch (e: Exception) {
            errorHandler.handleError(e, "There was a problem fetching the note")
            emit(RequestState.Error())
        }
    }

    fun updateNote() = liveData(viewModelScope.coroutineContext) {
        emit(RequestState.Loading)
        try {
            val response = noteRepository.updateNote(
                note = Note(
                    id = noteid,
                    title = title.value,
                    description = description.value,
                    image = image.value,
                    creationDate = createdDate,
                ),
            )

            emit(RequestState.Success(response))
        } catch (e: Exception) {
            errorHandler.handleError(e, "There was a problem updating the note")
            emit(RequestState.Error())
        }
    }

    fun createNote() = liveData(viewModelScope.coroutineContext) {
        emit(RequestState.Loading)
        try {
            val response = noteRepository.addNote(
                note = Note(
                    title = title.value,
                    description = description.value,
                    image = image.value,
                    creationDate = getCurrentDate(),
                ),
            )

            emit(RequestState.Success(response))
        } catch (e: Exception) {
            errorHandler.handleError(e, "There was a problem creating the note")
            emit(RequestState.Error())
        }
    }

    fun deleteNote() = liveData(viewModelScope.coroutineContext) {
        emit(RequestState.Loading)
        try {
            noteRepository.deleteNote(noteid)

            emit(RequestState.Success("Note deleted"))
        } catch (e: Exception) {
            errorHandler.handleError(e, "There was a problem deleting the note")
            emit(RequestState.Error())
        }
    }
}
