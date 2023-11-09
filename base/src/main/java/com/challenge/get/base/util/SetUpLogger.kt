package com.challenge.get.base.util

import timber.log.Timber

/**
 * Plant Timber for logging
 */
fun plantTimber(isDebug: Boolean = false) {
    if (isDebug) {
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String {
                return String.format(
                    "Class:%s: Line: %s, Method: %s",
                    super.createStackElementTag(element),
                    element.lineNumber,
                    element.methodName,
                )
            }
        })
    } else {
        Timber.plant(ReleaseTree())
    }
}