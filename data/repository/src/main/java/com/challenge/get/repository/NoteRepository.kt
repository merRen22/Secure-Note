package com.challenge.get.repository

import com.challenge.get.repository.external.NoteRemoteDataSource
import com.challenge.get.database.NoteDb
import com.challenge.get.model.Note
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for [Note]
 */
@Singleton
class NoteRepository @Inject constructor(
    private val noteApi: NoteRemoteDataSource,
    private val noteDb: NoteDb,
): NoteRemoteRepository {

    override suspend fun findAllUserNotes(): List<Note> {
        return noteDb.getAllFromUser()
    }

    override suspend fun findNoteById(id: Int): Note {
        return noteDb.getByID(id)
    }

    override suspend fun addNote(note: Note) {
        return noteDb.insert(note)
    }

    override suspend fun deleteNote(noteId: Int) {
        return noteDb.delete(noteId)
    }

    override suspend fun updateNote(note: Note) {
        return noteDb.update(note)
    }

    override suspend fun deleteAllUserNotes() {
        return noteDb.deleteAllFromUser()
    }

    override suspend fun getNotesFromService(): List<Note> {
        return noteApi.getNotes()
    }
}
