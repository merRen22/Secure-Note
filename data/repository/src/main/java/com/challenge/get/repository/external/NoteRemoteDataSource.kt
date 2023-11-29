package com.challenge.get.repository.external

import com.challenge.get.model.Note

/**
 * Note API
 */
interface NoteRemoteDataSource {
    /**
     * get list of [Note] list from remote.
     */
    suspend fun getNotes(): List<Note>
}