package com.joseph.exhibition.core.common.data.appwrite

/**
 * An interface for accessing AppWrite database documents as maps or values.
 */
interface AppWriteDbRepo {
    /**
     * Retrieves a document from the database as a map of keys and values.
     * @param documentId The ID of the document to retrieve.
     * @return A map of keys and values representing the document.
     */
    suspend fun getMap(documentId: String): Map<String, Any>

    /**
     * Retrieves a value from a document by its key.
     * @param documentId The ID of the document to retrieve the value from.
     * @param key The key of the value to retrieve.
     * @param default The default value to return if the key does not exist in the document.
     * @return The value associated with the key in the document, or the default value if not found.
     */
    suspend fun get(documentId: String, key: String, default: Any): Any

    /**
     * Retrieves a string value from a document by its key.
     * @param documentId The ID of the document to retrieve the value from.
     * @param key The key of the value to retrieve.
     * @param default The default value to return if the key does not exist in the document or is not a string.
     * @return The string value associated with the key in the document,
     * or the default value if not found or not a string.
     */
    suspend fun getString(documentId: String, key: String, default: String): String

    /**
     * Retrieves a boolean value from a document by its key.
     * @param documentId The ID of the document to retrieve the value from.
     * @param key The key of the value to retrieve.
     * @param default The default value to return if the key does not exist in the document or is not a boolean.
     * @return The boolean value associated with the key in the document,
     * or the default value if not found or not a boolean.
     */
    suspend fun getBoolean(documentId: String, key: String, default: Boolean): Boolean

    /**
     * Retrieves an integer value from a document by its key.
     * @param documentId The ID of the document to retrieve the value from.
     * @param key The key of the value to retrieve.
     * @param default The default value to return if the key does not exist in the document or is not an integer.
     * @return The integer value associated with the key in the document,
     * or the default value if not found or not an integer.
     */
    suspend fun getInt(documentId: String, key: String, default: Int): Int

    /**
     * Retrieves a long value from a document by its key.
     * @param documentId The ID of the document to retrieve the value from.
     * @param key The key of the value to retrieve.
     * @param default The default value to return if the key does not exist in the document or is not a long.
     * @return The long value associated with the key in the document, or the default value if not found or not a long.
     */
    suspend fun getLong(documentId: String, key: String, default: Long): Long

    /**
     * Retrieves a double value from a document by its key.
     * @param documentId The ID of the document to retrieve the value from.
     * @param key The key of the value to retrieve.
     * @param default The default value to return if the key does not exist in the document or is not a double.
     * @return The double value associated with the key in the document,
     * or the default value if not found or not a double.
     */
    suspend fun getDouble(documentId: String, key: String, default: Double): Double

    /**
     * Retrieves a float value from a document by its key.
     * @param documentId The ID of the document to retrieve the value from.
     * @param key The key of the value to retrieve.
     * @param default The default value to return if the key does not exist in
     * @return The float value associated with the key in the document,
     * or the default value if not found or not a double.
     */
    suspend fun getFloat(documentId: String, key: String, default: Float): Float
}
