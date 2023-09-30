package com.challenge.get.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.challenge.get.database.dao.NoteDao
import com.challenge.get.database.entity.NoteEntity

/**
 * App Database
 */
@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
}
