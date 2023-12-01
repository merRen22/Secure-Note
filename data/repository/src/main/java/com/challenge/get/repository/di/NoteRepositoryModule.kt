package com.challenge.get.repository.di

import com.challenge.get.database.NoteDb
import com.challenge.get.repository.NoteRemoteRepository
import com.challenge.get.repository.NoteRepository
import com.challenge.get.repository.external.NoteRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NoteRepositoryModule {

    @Singleton
    @Provides
    fun providesNoteRepository(
        noteDataSource: NoteRemoteDataSource,
        noteDb: NoteDb
    ): NoteRemoteRepository {
        return NoteRepository(noteDataSource, noteDb)
    }
}