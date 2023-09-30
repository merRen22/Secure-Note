package com.challenge.get.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Note(
    var id: Int = 0,
    var title: String = "",
    var description: String = "",
    var image: String = "",
    @SerialName("creation_date")
    var creationDate: String = "",
)
