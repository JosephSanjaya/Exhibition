package com.joseph.exhibition.core.common.data.cache

import com.joseph.exhibition.core.common.data.cache.model.CacheData
import java.time.temporal.ChronoUnit

/**
 * An interface that defines the contract for a cache repository.
 */
interface CacheRepo {
    companion object {
        // A constant for the named qualifier for dependency injection
        const val NAMED = "cache"

        // A constant for the default time-to-live duration in hours
        const val DEFAULT_TTL_DURATION = 2L
    }

    /**
     * Gets a cached value by a key, or invokes a suspend function to get a new value if the cache is null or expired.
     * @param T the type of the value to be cached or retrieved.
     * @param key the key associated with the value.
     * @param ttl the time-to-live duration for the value. Defaults to the default duration defined in CacheRepo.
     * @param onCacheMiss the suspend function that returns a new value when the cache is null or expired.
     * @return the cached or new value of type T.
     */
    suspend fun <T> get(
        key: String,
        ttl: Pair<Long, ChronoUnit> = DEFAULT_TTL_DURATION to ChronoUnit.HOURS,
        onCacheMiss: suspend () -> T,
    ): T

    /**
     * Creates a cache data object with a key, a value, and a time-to-live duration.
     * @param T the type of the value to be cached.
     * @param key the key associated with the value.
     * @param value the value to be cached.
     * @param ttl the time-to-live duration for the value. Defaults to the default duration defined in CacheRepo.
     * @return a cache data object of type T.
     */
    fun <T> create(
        key: String,
        value: T,
        ttl: Pair<Long, ChronoUnit> = DEFAULT_TTL_DURATION to ChronoUnit.HOURS,
    ): CacheData<T>
}
