package com.challenge.get.database

import com.challenge.get.model.Note

/**
 * [Note] DB
 */
interface NoteDb {
    /**
     * get all [Note] based on user id, data is paginated
     */
    suspend fun getAllFromUser(): List<Note>

    /**
     * get [Note] by id
     */
    suspend fun getByID(id: Int): Note

    /**
     * insert [Note]
     */
    suspend fun insert(note: Note)

    /**
     * delete [Note]
     */
    suspend fun delete(noteId: Int)

    /**
     * Delete all [Note] from a User
     */
    suspend fun deleteAllFromUser()

    /**
     * update [Note]
     */
    suspend fun update(note: Note)
}
