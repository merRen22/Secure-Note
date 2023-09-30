package com.challenge.get.database

import com.challenge.get.database.dao.NoteDao
import com.challenge.get.database.entity.NoteEntity
import com.challenge.get.model.Note
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class NoteDbClient(private val noteDao: NoteDao) : NoteDb {
    override suspend fun getAllFromUser(): List<Note> =
        noteDao.getAllFromUser().map { it.toModel() }

    override suspend fun getByID(id: Int): Note =
        noteDao.getByID(id).toModel()

    override suspend fun insert(note: Note) {
        withContext(IO) {
            noteDao.insert(NoteEntity.fromModel(note))
        }
    }

    override suspend fun delete(noteId: Int) {
        withContext(IO) {
            noteDao.delete(noteId)
        }
    }

    override suspend fun deleteAllFromUser() {
        withContext(IO) {
            noteDao.deleteAllFromUser()
        }
    }

    override suspend fun update(note: Note) {
        withContext(IO) {
            noteDao.update(NoteEntity.fromModel(note))
        }
    }
}
