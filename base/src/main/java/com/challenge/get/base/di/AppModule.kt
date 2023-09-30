package com.challenge.get.base.di

import android.app.Application
import com.challenge.get.base.AppErrorHandler
import com.challenge.get.base.ErrorHandler
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
    fun provideErrorHandler(context: Application): ErrorHandler {
        return AppErrorHandler(context)
    }

}