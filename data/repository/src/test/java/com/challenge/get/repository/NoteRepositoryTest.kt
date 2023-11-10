package com.challenge.get.repository

import com.challenge.get.database.NoteDb
import com.challenge.get.model.Note
import com.challenge.get.repository.external.NoteRemoteDataSource
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Unit test for [NoteRepository].
 */
@RunWith(MockitoJUnitRunner::class)
class NoteRepositoryTest {

    @Mock
    lateinit var noteApi: NoteRemoteDataSource

    @Mock
    lateinit var noteDb: NoteDb

    private lateinit var repository: NoteRemoteRepository

    // fake request and response
    //private lateinit var responseApiError: RequestState.Error
    //private lateinit var responseSuccess: RequestState.Success<List<Note>>
    private lateinit var responseSuccess: List<Note>

    @Before
    fun setUp() {
        repository = NoteRepository(noteApi, noteDb)
        responseSuccess = listOf(
            Note()
        )
    }

    @Test
    fun `findAllUserNotes() should return Success`() = runBlocking {
        // given
        Mockito.`when`(noteDb.getAllFromUser()).thenReturn(responseSuccess)
        // when
        val result = runBlocking { repository.findAllUserNotes() }
        // then
        Truth.assertThat(result).isEqualTo(responseSuccess)
    }
}