package com.challenge.get.repository

import com.challenge.get.database.NoteDb
import com.challenge.get.model.Note
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for [Note]
 */
@Singleton
class NoteRepository @Inject constructor(
    private val noteDb: NoteDb,
) {
    /**
     * Makes a request to the local DB to get the notes from one user
     */
    suspend fun findAllUserNotes(): List<Note> {
        return noteDb.getAllFromUser()
    }

    /**
     * Makes a request to the local DB to get the notes based on the ID
     */
    suspend fun findNoteById(id: Int): Note {
        return noteDb.getByID(id)
    }

    /**
     * Inserts a [note] to the local db
     */
    suspend fun addNote(note: Note) {
        return noteDb.insert(note)
    }

    /**
     * Deletes a [note] from the local db
     */
    suspend fun deleteNote(noteId: Int) {
        return noteDb.delete(noteId)
    }

    /**
     * Updates a [note] from the local db
     */
    suspend fun updateNote(note: Note) {
        return noteDb.update(note)
    }

    /**
     * Deletes all [note] from a user from the local db
     */
    suspend fun deleteAllUserNotes() {
        return noteDb.deleteAllFromUser()
    }
}
