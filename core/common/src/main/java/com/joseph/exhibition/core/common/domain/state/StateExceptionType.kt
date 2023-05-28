package com.joseph.exhibition.core.common.domain.state

/**
 * An enum class that defines the types of state exceptions.
 */
enum class StateExceptionType {
    /**
     * An exception type that indicates an appwrite error.
     */
    APPWRITE,

    /**
     * An exception type that indicates a network error.
     */
    NETWORK,

    /**
     * An exception type that indicates a timeout error.
     */
    TIMEOUT,

    /**
     * An exception type that indicates an unknown error.
     */
    UNKNOWN
}
