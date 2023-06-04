package com.joseph.exhibition.core.common.data.cache.model

import com.google.errorprone.annotations.Keep
import com.joseph.exhibition.core.common.data.cache.CacheRepo
import java.time.Instant
import java.time.temporal.ChronoUnit

/**
 * A data class that represents a cached value with a key and a time-to-live duration.
 * @param T the type of the value to be cached.
 * @param key the key associated with the value.
 * @param value the value to be cached.
 * @param created the instant when the value was cached. Defaults to the current instant.
 * @param ttl the time-to-live duration for the value. Defaults to the default duration defined in CacheRepo.
 */
@Keep
data class CacheData<T>(
    val key: String,
    val value: T,
    val created: Instant = Instant.now(),
    val ttl: Pair<Long, ChronoUnit> = CacheRepo.DEFAULT_TTL_DURATION to ChronoUnit.HOURS,
) {
    /**
     * Checks if the cached value is expired based on the current time and the time-to-live duration.
     * @return true if the cached value is expired, false otherwise.
     */
    fun isExpired(): Boolean {
        // Get the current time
        val now = Instant.now()
        // Get the expiration time by adding the time-to-live value to the created time
        val expiration = created.plus(ttl.first, ttl.second)
        // Compare the current time and the expiration time
        return now.isAfter(expiration)
    }
}
