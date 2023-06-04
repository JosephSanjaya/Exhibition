package com.joseph.exhibition.core.common.data.remoteconfig

/**
 * An interface for retrieving configuration values from a remote config repository.
 */
interface ConfigRepo {
    /**
     * Retrieves a boolean flag associated with the given [key] from the remote config repository.
     *
     * @param key The key associated with the flag.
     * @return The value of the flag as a [Boolean],
     * or a default value if the flag is not found or an error occurs.
     */
    suspend fun getFlag(key: String): Boolean

    /**
     * Retrieves a string value associated with the given [key] from the remote config repository.
     *
     * @param key The key associated with the string value.
     * @return The value of the string as a [String],
     * or a default value if the string is not found or an error occurs.
     */
    suspend fun getString(key: String): String

    /**
     * Retrieves an integer value associated with the given [key] from the remote config repository.
     *
     * @param key The key associated with the integer value.
     * @return The value of the integer as an [Int],
     * or a default value if the integer is not found or an error occurs.
     */
    suspend fun getInt(key: String): Int
}
