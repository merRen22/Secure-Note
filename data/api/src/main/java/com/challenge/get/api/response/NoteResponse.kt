package com.challenge.get.api.response

import com.challenge.get.model.Note
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response data for [Note]
 */
@Serializable
class NoteResponse {

    @SerialName("id")
    var id: Int? = null

    @SerialName("title")
    var title: String? = null

    @SerialName("description")
    var description: String? = null

    @SerialName("image")
    var image: String? = null

    @SerialName("creation_date")
    var creationDate: String? = null

    fun toModel() = Note(
        id = id ?: 0,
        title = title ?: "",
        description = description ?: "",
        image = image ?: "",
        creationDate = creationDate ?: ""
    )
}