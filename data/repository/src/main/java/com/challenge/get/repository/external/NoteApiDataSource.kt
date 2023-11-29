package com.challenge.get.repository.external

import com.challenge.get.api.NoteApi
import com.challenge.get.model.Note
import com.challenge.get.repository.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


/**
 * Notes API client
 */
class NoteApiDataSource @Inject constructor(
    @IoDispatcher private val coroutineDispatcher: CoroutineDispatcher,
    private val noteApi: NoteApi
) : NoteRemoteDataSource {

    override suspend fun getNotes(): List<Note> {
        return withContext(coroutineDispatcher) {
            noteApi.getNotes().items?.let {
                it.map { item ->
                    item.toModel()
                }
            } ?: emptyList()
        }
    }
}