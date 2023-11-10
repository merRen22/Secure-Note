package com.challenge.get.repository.di

import com.challenge.get.api.NoteApi
import com.challenge.get.repository.external.NoteApiDataSource
import com.challenge.get.repository.external.NoteRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton
import kotlin.coroutines.coroutineContext

@Module
@InstallIn(SingletonComponent::class)
object NoteDataSourceModule {

    @Singleton
    @Provides
    fun providesNoteDataSource(
        noteApi: NoteApi,
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): NoteRemoteDataSource {
        return NoteApiDataSource(
            coroutineDispatcher = coroutineDispatcher,
            noteApi = noteApi
        )
    }
}