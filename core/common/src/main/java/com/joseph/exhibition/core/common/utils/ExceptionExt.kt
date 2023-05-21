package com.joseph.exhibition.core.common.utils

import android.util.Log
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import java.lang.Exception

/**
 * A helper function to record exceptions with Firebase Crashlytics and log them to the console.
 * @param throwable The throwable object to record and log.
 * @param params The optional custom key-value pairs to associate with the exception in Crashlytics.
 * The values are converted to the appropriate data type based on their runtime type.
 */
fun Any.recordException(throwable: Throwable, vararg params: Pair<String, Any>) {
    // Use a private function to log and set custom keys
    logAndSetCustomKeys(throwable.message ?: "Unknown error", *params)
    // Log the throwable object to the console
    Log.e(this::class.simpleName, throwable.message, throwable)
    // Record the throwable object to Crashlytics
    Firebase.crashlytics.recordException(throwable)
}

/**
 * A helper function to record exceptions with Firebase Crashlytics and log them to the console.
 * @param msg The message to record and log.
 * @param params The optional custom key-value pairs to associate with the exception in Crashlytics.
 * The values are converted to the appropriate data type based on their runtime type.
 */
fun Any.recordException(msg: String, vararg params: Pair<String, Any>) {
    // Use a private function to log and set custom keys
    logAndSetCustomKeys(msg, *params)
    // Create an exception object with the message
    val exception = Exception(msg)
    // Log the exception object to the console
    Log.e(this::class.simpleName, msg, exception)
    // Record the exception object to Crashlytics
    Firebase.crashlytics.recordException(exception)
}

/**
 * A private function to log a message and set custom keys with Firebase Crashlytics.
 * @param msg The message to log.
 * @param params The custom key-value pairs to set.
 * The values are converted to the appropriate data type based on their runtime type.
 */
private fun Any.logAndSetCustomKeys(msg: String, vararg params: Pair<String, Any>) {
    // Use a default tag that is more descriptive and consistent with your app name or package name
    val tag = this::class.simpleName
    // Log the tag and message to Crashlytics
    Firebase.crashlytics.log("Tag: $tag")
    Firebase.crashlytics.log("Message: $msg")
    // Set the custom keys with Firebase Crashlytics
    params.forEach { (key, value) ->
        when (value) {
            is Boolean -> Firebase.crashlytics.setCustomKey(key, value)
            is Int -> Firebase.crashlytics.setCustomKey(key, value)
            is Long -> Firebase.crashlytics.setCustomKey(key, value)
            is Double -> Firebase.crashlytics.setCustomKey(key, value)
            is Float -> Firebase.crashlytics.setCustomKey(key, value)
            else -> Firebase.crashlytics.setCustomKey(key, value.toString())
        }
    }
    Firebase.crashlytics.recordException(Throwable(msg))
}
