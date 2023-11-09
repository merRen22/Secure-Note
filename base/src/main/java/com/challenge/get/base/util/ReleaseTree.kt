package com.challenge.get.base.util

import android.util.Log
import org.jetbrains.annotations.NotNull
import timber.log.Timber

/** For production
 * Instead of loggin in console we send the log to crashlytics
 */
class ReleaseTree : @NotNull Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.ERROR || priority == Log.WARN){
            // Todo SEND ERROR REPORTS TO YOUR Crashlytics.
        }
    }
}