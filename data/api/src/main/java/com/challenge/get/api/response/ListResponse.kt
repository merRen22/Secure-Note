package com.challenge.get.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response data with [NoteResponse] list in it.
 */
@Serializable
class ListResponse {
    @SerialName("recipes")
    var items: List<NoteResponse>? = null
}