package com.challenge.get

import android.app.Application
import com.challenge.get.base.util.plantTimber
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class App : Application() {
    override fun onCreate() {
        super.onCreate()
        plantTimber(BuildConfig.DEBUG)
    }
}
