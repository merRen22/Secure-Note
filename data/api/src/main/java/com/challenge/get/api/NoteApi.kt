package com.challenge.get.api

import com.challenge.get.api.response.ListResponse
import retrofit2.http.GET

interface NoteApi {

    @GET("recipes_data.json")
    suspend fun getNotes(): ListResponse
}
