package com.joseph.exhibition.core.common.data.cache

import android.content.SharedPreferences
import com.google.gson.Gson
import com.joseph.exhibition.core.common.data.cache.model.CacheData
import com.joseph.exhibition.core.common.utils.Logger
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyAll
import java.time.Instant
import java.time.temporal.ChronoUnit

class CacheDataSourceTest : FunSpec({

    // Mock the dependencies of the cache data source
    val sharedPreferences = mockk<SharedPreferences>()
    val gson = mockk<Gson>()
    val logger = mockk<Logger>()

    // Create the cache data source instance to test
    val cacheDataSource = CacheDataSource(sharedPreferences, gson, logger)

    // Define some constants for testing
    val key = "test_key"
    val value = "test_value"
    val ttl = 10L to ChronoUnit.MINUTES

    // Define a lambda function to simulate a cache miss
    val onCacheMiss: suspend () -> String = { value }

    // Define a valid cache data object
    val validCacheData = CacheData(key, value, Instant.now(), ttl)

    beforeTest {
        // Clear all mocks before each test
        clearAllMocks()
    }

    test("get should return the value from the cache if it exists and is not expired") {
        // Mock the shared preferences to return a valid json string for the key
        every { sharedPreferences.getString(key, null) } returns "valid_json"

        // Mock the gson to deserialize the json string to a valid cache data object
        every {
            gson.fromJson<CacheData<String>>(
                "valid_json",
                CacheData::class.java
            )
        } returns validCacheData

        // Call the get method with the key and ttl
        val result = cacheDataSource.get(key, ttl, onCacheMiss)

        // Verify that the result is the same as the value of the valid cache data object
        result shouldBe value

        // Verify that no other interactions happened with the mocks
        verifyAll { sharedPreferences.getString(key, null) }
        verifyAll { gson.fromJson<CacheData<String>>("valid_json", CacheData::class.java) }
        confirmVerified(sharedPreferences, gson)
    }
})
