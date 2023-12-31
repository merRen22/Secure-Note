package com.challenge.get.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.challenge.get.base.AppErrorHandler
import com.challenge.get.repository.util.RequestState
import com.challenge.get.model.Note
import com.challenge.get.repository.NoteRemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * [ViewModel] to manage home view data.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteRepository: NoteRemoteRepository,
    private val errorHandler: AppErrorHandler,
) : ViewModel() {

    private var allNotes: List<Note> = listOf()
    val mutableNotes = MutableLiveData<List<Note>>()

    fun fetchNotes() = liveData(viewModelScope.coroutineContext) {
        emit(RequestState.Loading)
        try {
            val notes = noteRepository.findAllUserNotes()
            allNotes = notes

            emit(RequestState.Success(notes))
        } catch (e: Exception) {
            errorHandler.handleError(e, "There was a problem getting your notes")
            emit(RequestState.Error())
        }
    }

    fun filterNotes(searchTerm: String) {
        if (allNotes.isEmpty()) return
        if (searchTerm.isEmpty()) {
            mutableNotes.value = allNotes
            return
        }

        mutableNotes.value = allNotes.filter {
            it.title.contains(searchTerm, ignoreCase = true)
        }
    }
}
