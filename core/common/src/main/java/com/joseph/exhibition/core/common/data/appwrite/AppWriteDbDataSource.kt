package com.joseph.exhibition.core.common.data.appwrite

import arrow.core.getOrElse
import arrow.core.getOrNone
import arrow.core.raise.either
import arrow.core.raise.ensure
import com.joseph.exhibition.core.common.utils.recordException
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.appwrite.services.Databases

/**
 * A data source class that implements the AppWriteDbRepo interface using the AppWrite Databases API.
 * @param db The Databases object that provides access to the AppWrite database service.
 * @param databaseId The ID of the database to use.
 * @param collectionId The ID of the collection to use.
 */
class AppWriteDbDataSource @AssistedInject constructor(
    private val db: Databases,
    @Assisted("databaseId") private val databaseId: String,
    @Assisted("collectionId") private val collectionId: String,
) : AppWriteDbRepo {

    /**
     * A helper function that tries to cast a value from a map to a given type,
     * or returns a default value if not possible.
     * @param key The key of the value to cast.
     * @param default The default value to return if the casting fails.
     * @return The casted value or the default value.
     */
    private inline fun <reified T> Map<String, Any>.getByKeyAs(
        key: String,
        default: T,
    ): T {
        val result = this.getOrDefault(key, default)
        return either {
            ensure(result is T) {
                "Type casting failure for key: $key, returning as default"
            }
            result
        }.onLeft {
            recordException(
                it,
                "databaseId" to databaseId,
                "collectionId" to collectionId,
                "key" to key
            )
        }.getOrElse {
            default
        }
    }

    override suspend fun getMap(documentId: String): Map<String, Any> {
        return db.getDocument(databaseId, collectionId, documentId).data
    }

    override suspend fun get(documentId: String, key: String, default: Any): Any {
        return getMap(documentId).getOrNone(key)
    }

    override suspend fun getString(documentId: String, key: String, default: String): String {
        return getMap(documentId).getByKeyAs(key, default)
    }

    override suspend fun getBoolean(documentId: String, key: String, default: Boolean): Boolean {
        return getMap(documentId).getByKeyAs(key, default)
    }

    override suspend fun getInt(documentId: String, key: String, default: Int): Int {
        return getMap(documentId).getByKeyAs(key, default)
    }

    override suspend fun getLong(documentId: String, key: String, default: Long): Long {
        return getMap(documentId).getByKeyAs(key, default)
    }

    override suspend fun getDouble(documentId: String, key: String, default: Double): Double {
        return getMap(documentId).getByKeyAs(key, default)
    }

    override suspend fun getFloat(documentId: String, key: String, default: Float): Float {
        return getMap(documentId).getByKeyAs(key, default)
    }
}
