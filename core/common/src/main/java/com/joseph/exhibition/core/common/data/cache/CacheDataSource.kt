package com.joseph.exhibition.core.common.data.cache

import android.content.SharedPreferences
import android.security.keystore.KeyExpiredException
import androidx.core.content.edit
import com.google.gson.Gson
import com.joseph.exhibition.core.common.data.cache.model.CacheData
import com.joseph.exhibition.core.common.utils.Logger
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import javax.inject.Named

/**
 * A data source class that provides cached data from shared preferences.
 * @param sharedPreferences The shared preferences instance to store and retrieve data.
 * @param gson The gson instance to serialize and deserialize data.
 */
class CacheDataSource @Inject constructor(
    @Named(CacheRepo.NAMED) private val sharedPreferences: SharedPreferences,
    private val gson: Gson,
    private val logger: Logger
) : CacheRepo {

    override suspend fun <T> get(
        key: String,
        ttl: Pair<Long, ChronoUnit>,
        onCacheMiss: suspend () -> T
    ): T {
        val cached = sharedPreferences.getString(key, null)
        return try {
            requireNotNull(cached) { KotlinNullPointerException() }
            // Use a private function to deserialize the cache data
            val result = gson.fromJson<CacheData<T>>(cached, CacheData::class.java)
            require(!result.isExpired()) { KeyExpiredException() }
            result
        } catch (e: Exception) {
            logger.logIssueAsNonFatal(e)
            val newData = create(key, onCacheMiss(), ttl)
            // Use apply to update and return the shared preferences value
            sharedPreferences.edit { putString(key, gson.toJson(newData)) }
            newData
        }.value
    }

    override fun <T> create(
        key: String,
        value: T,
        ttl: Pair<Long, ChronoUnit>,
    ): CacheData<T> {
        return CacheData(key, value, Instant.now(), ttl)
    }

    /**
     * Dagger module for binding the [CacheDataSource] implementation to [CacheRepo].
     */
    @InstallIn(SingletonComponent::class)
    @Module
    internal abstract class Binder {

        /**
         * Binds the implementation of [CacheDataSource] to [CacheRepo].
         *
         * @param impl The implementation of [CacheDataSource] to bind.
         * @return The bound [CacheRepo] instance.
         */
        @Binds
        abstract fun bind(impl: CacheDataSource): CacheRepo
    }
}
