package com.joseph.exhibition.core.common.utils

/**
 * An interface for logging and reporting issues, including exceptions, to a logging system.
 */
interface Logger {

    /**
     * A helper function to record exceptions with Firebase Crashlytics and log them to the console.
     * @param throwable The throwable object to record and log.
     * @param params The optional custom key-value pairs to associate with the exception in Crashlytics.
     * The values are converted to the appropriate data type based on their runtime type.
     */
    fun logIssueAsNonFatal(throwable: Throwable, vararg params: Pair<String, Any>)

    /**
     * A helper function to record exceptions with Firebase Crashlytics and log them to the console.
     * @param msg The message to record and log.
     * @param params The optional custom key-value pairs to associate with the exception in Crashlytics.
     * The values are converted to the appropriate data type based on their runtime type.
     */
    fun logIssueAsNonFatal(msg: String, vararg params: Pair<String, Any>)
}
