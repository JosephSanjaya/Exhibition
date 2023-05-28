package com.joseph.exhibition.core.common.domain.state

/**
 * A custom exception class that represents the state errors.
 * @param message The message of the exception.
 * @param type The type of the exception.
 * @param code The optional code of the exception.
 * @param response The optional response of the exception.
 */
class StateException(
    override val message: String,
    val type: StateExceptionType,
    val code: Int? = null,
    val response: String? = null
) : Throwable(message)
