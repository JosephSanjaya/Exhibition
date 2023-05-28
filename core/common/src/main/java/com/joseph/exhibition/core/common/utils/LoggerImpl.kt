package com.joseph.exhibition.core.common.utils

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import javax.inject.Inject

/**
 * Implementation of the [Logger] interface that logs issues as non-fatal errors using Firebase Crashlytics
 * and logs them to the console.
 */
class LoggerImpl @Inject constructor(
    private val crashlytics: FirebaseCrashlytics
) : Logger {

    override fun logIssueAsNonFatal(throwable: Throwable, vararg params: Pair<String, Any>) {
        logAndSetCustomKeys(throwable.message ?: "Unknown error", *params)
        Log.e(getCallingClassName(), throwable.message, throwable)
    }

    override fun logIssueAsNonFatal(msg: String, vararg params: Pair<String, Any>) {
        logAndSetCustomKeys(msg, *params)
        Log.e(getCallingClassName(), msg, Throwable(msg))
    }

    /**
     * A private function to log a message and set custom keys with Firebase Crashlytics.
     * @param msg The message to log.
     * @param params The custom key-value pairs to set.
     * The values are converted to the appropriate data type based on their runtime type.
     */
    private fun logAndSetCustomKeys(msg: String, vararg params: Pair<String, Any>) {
        // Use a default tag that is more descriptive and consistent with your app name or package name
        val tag = getCallingClassName()
        // Log the tag and message to Crashlytics
        crashlytics.log("Tag: $tag")
        crashlytics.log("Message: $msg")
        // Set the custom keys with Firebase Crashlytics
        params.forEach { (key, value) ->
            when (value) {
                is Boolean -> crashlytics.setCustomKey(key, value)
                is Int -> crashlytics.setCustomKey(key, value)
                is Long -> crashlytics.setCustomKey(key, value)
                is Double -> crashlytics.setCustomKey(key, value)
                is Float -> crashlytics.setCustomKey(key, value)
                else -> crashlytics.setCustomKey(key, value.toString())
            }
        }
        crashlytics.recordException(Throwable(msg))
    }

    /**
     * Returns the calling class name by examining the stack trace.
     * @return The simple name of the calling class.
     */
    private fun getCallingClassName(): String {
        val stackTrace = Thread.currentThread().stackTrace
        var callingClassName: String? = null

        for (element in stackTrace) {
            val className = element.className
            callingClassName = if (className != LoggerImpl::class.java.name) {
                className.substringAfterLast('.')
            } else {
                // Skip the stack frames within LoggerImpl class
                null
            }
        }

        return callingClassName ?: "Unknown"
    }
}
