package com.challenge.get.repository

import com.challenge.get.model.Note

interface NoteRemoteRepository {

    /**
     * Makes a request to the local DB to get the notes from one user
     */
    suspend fun findAllUserNotes(): List<Note>

    /**
     * Makes a request to the local DB to get the notes based on the ID
     */
    suspend fun findNoteById(id: Int): Note

    /**
     * Inserts a [note] to the local db
     */
    suspend fun addNote(note: Note)

    /**
     * Deletes a [note] from the local db
     */
    suspend fun deleteNote(noteId: Int)

    /**
     * Updates a [note] from the local db
     */
    suspend fun updateNote(note: Note)

    /**
     * Deletes all [note] from a user from the local db
     */
    suspend fun deleteAllUserNotes()

    suspend fun getNotesFromService(): List<Note>
}