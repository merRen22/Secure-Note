package com.challenge.get.base.di

import android.app.Application
import android.content.Context
import com.challenge.get.base.AppErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideErrorHandler(context: Application): AppErrorHandler {
        return AppErrorHandler(context)
    }

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}