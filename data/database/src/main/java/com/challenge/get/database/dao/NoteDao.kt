package com.challenge.get.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.challenge.get.database.entity.NoteEntity

/**
 * [Note] Dao
 */
@Dao
abstract class NoteDao {

    @Query("SELECT * FROM note")
    abstract suspend fun getAllFromUser(): List<NoteEntity>

    @Query("SELECT * FROM note WHERE id=:id")
    abstract suspend fun getByID(id: Int): NoteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(detailEntity: NoteEntity)

    @Query("DELETE FROM note WHERE id=:id")
    abstract suspend fun delete(id: Int)

    @Update
    abstract suspend fun update(note: NoteEntity)

    @Query("DELETE FROM note")
    abstract suspend fun deleteAllFromUser()
}
