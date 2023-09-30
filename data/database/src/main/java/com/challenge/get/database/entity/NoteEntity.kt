package com.challenge.get.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.challenge.get.model.Note

@Entity(
    tableName = "note",
)
class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val creationDate: String,
) {

    fun toModel() = Note(
        id = id,
        title = title,
        description = description,
        image = image,
        creationDate = creationDate,
    )

    companion object {
        fun fromModel(note: Note): NoteEntity {
            return NoteEntity(
                id = note.id,
                title = note.title,
                description = note.description,
                image = note.image,
                creationDate = note.creationDate,
            )
        }
    }
}
