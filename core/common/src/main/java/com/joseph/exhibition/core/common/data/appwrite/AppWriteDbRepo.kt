package com.joseph.exhibition.core.common.data.appwrite

/**
 * An interface for accessing AppWrite database documents as maps or values.
 */
interface AppWriteDbRepo {
    /**
     * Retrieves a document from the database as a map of keys and values.
     * @param documentId The ID of the document to retrieve.
     * @param queries The optional query parameters to filter the document by.
     * The queries are strings that follow the AppWrite query syntax.
     * For example, Query.between("a", 1, 0) returns documents where the value
     * of the "a" field is between 1 and 0.
     * @return A map of keys and values representing the document.
     */
    suspend fun getMap(documentId: String, vararg queries: String): Map<String, Any>

    /**
     * Retrieves a value from a document by its key.
     * @param documentId The ID of the document to retrieve the value from.
     * @param key The key of the value to retrieve.
     * @param default The default value to return if the key does not exist in the document.
     * @param queries The optional query parameters to filter the document by.
     * The queries are strings that follow the AppWrite query syntax.
     * For example, Query.between("a", 1, 0) returns documents where the value
     * of the "a" field is between 1 and 0.
     * @return The value associated with the key in the document, or the default value if not found.
     */
    suspend fun get(documentId: String, key: String, default: Any, vararg queries: String): Any

    /**
     * Retrieves a string value from a document by its key.
     * @param documentId The ID of the document to retrieve the value from.
     * @param key The key of the value to retrieve.
     * @param default The default value to return if the key does not exist in the document or is not a string.
     * @param queries The optional query parameters to filter the document by.
     * The queries are strings that follow the AppWrite query syntax.
     * For example, Query.between("a", 1, 0) returns documents where the value
     * of the "a" field is between 1 and 0.
     * @return The string value associated with the key in the document,
     * or the default value if not found or not a string.
     */
    suspend fun getString(documentId: String, key: String, default: String, vararg queries: String): String

    /**
     * Retrieves a boolean value from a document by its key.
     * @param documentId The ID of the document to retrieve the value from.
     * @param key The key of the value to retrieve.
     * @param default The default value to return if the key does not exist in the document or is not a boolean.
     * @param queries The optional query parameters to filter the document by.
     * The queries are strings that follow the AppWrite query syntax.
     * For example, Query.between("a", 1, 0) returns documents where the value
     * of the "a" field is between 1 and 0.
     * @return The boolean value associated with the key in the document,
     * or the default value if not found or not a boolean.
     */
    suspend fun getBoolean(documentId: String, key: String, default: Boolean, vararg queries: String): Boolean

    /**
     * Retrieves an integer value from a document by its key.
     * @param documentId The ID of the document to retrieve the value from.
     * @param key The key of the value to retrieve.
     * @param default The default value to return if the key does not exist in the document or is not an integer.
     * @param queries The optional query parameters to filter the document by.
     * The queries are strings that follow the AppWrite query syntax.
     * For example, Query.between("a", 1, 0) returns documents where the value
     * of the "a" field is between 1 and 0.
     * @return The integer value associated with the key in the document,
     * or the default value if not found or not an integer.
     */
    suspend fun getInt(documentId: String, key: String, default: Int, vararg queries: String): Int

    /**
     * Retrieves a long value from a document by its key.
     * @param documentId The ID of the document to retrieve the value from.
     * @param key The key of the value to retrieve.
     * @param default The default value to return if the key does not exist in the document or is not a long.
     * @param queries The optional query parameters to filter the document by.
     * The queries are strings that follow the AppWrite query syntax.
     * For example, Query.between("a", 1, 0) returns documents where the value
     * of the "a" field is between 1 and 0.
     * @return The long value associated with the key in the document, or the default value if not found or not a long.
     */
    suspend fun getLong(documentId: String, key: String, default: Long, vararg queries: String): Long

    /**
     * Retrieves a double value from a document by its key.
     * @param documentId The ID of the document to retrieve the value from.
     * @param key The key of the value to retrieve.
     * @param default The default value to return if the key does not exist in the document or is not a double.
     * @param queries The optional query parameters to filter the document by.
     * The queries are strings that follow the AppWrite query syntax.
     * For example, Query.between("a", 1, 0) returns documents where the value
     * of the "a" field is between 1 and 0.
     * @return The double value associated with the key in the document,
     * or the default value if not found or not a double.
     */
    suspend fun getDouble(documentId: String, key: String, default: Double, vararg queries: String): Double

    /**
     * Retrieves a float value from a document by its key.
     * @param documentId The ID of the document to retrieve the value from.
     * @param key The key of the value to retrieve.
     * @param default The default value to return if the key does not exist in
     * @param queries The optional query parameters to filter the document by.
     * The queries are strings that follow the AppWrite query syntax.
     * For example, Query.between("a", 1, 0) returns documents where the value
     * of the "a" field is between 1 and 0.
     * @return The float value associated with the key in the document,
     * or the default value if not found or not a double.
     */
    suspend fun getFloat(documentId: String, key: String, default: Float, vararg queries: String): Float
}
