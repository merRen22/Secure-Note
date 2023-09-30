package com.challenge.get.base.util

import android.os.Build
import java.text.SimpleDateFormat
import java.util.Calendar

/**
 * Get current date and time in format 2023-07-31T23:17:14.225+00:00
 */
fun getCurrentDate(): String {
    val currentDateTime = Calendar.getInstance().time
    val simpleDateFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        SimpleDateFormat(DATE_FORMAT)
    } else {
        SimpleDateFormat(DATE_FORMAT_LEGACY)
    }
    return simpleDateFormat.format(currentDateTime)
}

/**
 * From format to DD/MM/YYYY
 */
fun String.formatDate(): String {
    val simpleDateFormat = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        SimpleDateFormat(DATE_FORMAT)
    } else {
        SimpleDateFormat(DATE_FORMAT_LEGACY)
    }
    val date = simpleDateFormat.parse(this)
    val newFormat = SimpleDateFormat("dd/MM/yyyy")
    return newFormat.format(date)
}

const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
const val DATE_FORMAT_LEGACY = "yyyy-MM-dd'T'HH:mm:ss.SSS"
