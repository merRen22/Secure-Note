package com.challenge.get.database.di

import android.app.Application
import androidx.room.Room
import com.challenge.get.database.AppDatabase
import com.challenge.get.database.NoteDb
import com.challenge.get.database.NoteDbClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "database-name").build()
    }

    @Provides
    @Singleton
    fun provideNoteDb(appDatabase: AppDatabase): NoteDb {
        return NoteDbClient(appDatabase.noteDao)
    }
}