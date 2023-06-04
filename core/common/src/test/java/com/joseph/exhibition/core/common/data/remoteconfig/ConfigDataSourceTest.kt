package com.joseph.exhibition.core.common.data.remoteconfig

import com.joseph.exhibition.core.common.data.appwrite.AppWriteDbFactory
import com.joseph.exhibition.core.common.data.appwrite.AppWriteDbRepo
import com.joseph.exhibition.core.common.data.cache.CacheRepo
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import org.mockito.kotlin.any

class ConfigDataSourceTest : FunSpec({

    // Create mock instances of the dependencies
    val factory = mockk<AppWriteDbFactory>()
    val cacheRepo = mockk<CacheRepo>()
    val source = mockk<AppWriteDbRepo>()

    // Create an instance of FlagConfigDataSource with the mock dependencies
    val configDataSource by lazy {
        ConfigDataSource(factory, cacheRepo)
    }

    beforeTest {
        // Stub the factory to return the mock source
        coEvery { factory.create(any(), any()) } returns any()
    }

    test("getFlag should return true if the cache and source have true value") {
        // Arrange
        coEvery { source.getBoolean(any(), any(), any()) } returns true
        coEvery {
            cacheRepo.get(
                any(),
                any(),
                any<suspend () -> Boolean>()
            )
        } returns true

        // Act
        val result = configDataSource.getFlag("test")

        // Assert
        result shouldBe true
    }

    test("getFlag should return false if the cache and source have false value") {
        // Arrange
        coEvery { source.getBoolean(any(), any(), any()) } returns false
        coEvery {
            cacheRepo.get(
                any(),
                any(),
                any<suspend () -> Boolean>()
            )
        } returns false

        // Act
        val result = configDataSource.getFlag("test")

        // Assert
        result shouldBe false
    }

    test("getFlag should return true if the cache has null value and source has true value") {
        // Arrange
        coEvery { source.getBoolean(any(), any(), any()) } returns true
        coEvery {
            cacheRepo.get(
                any(),
                any(),
                any<suspend () -> Boolean>()
            )
        } returns true

        // Act
        val result = configDataSource.getFlag("test")

        // Assert
        result shouldBe true
    }

    test("getFlag should return false if the cache has null value and source has false value") {
        // Arrange
        coEvery { source.getBoolean(any(), any(), any()) } returns false
        coEvery {
            cacheRepo.get(
                any(),
                any(),
                any<suspend () -> Boolean>()
            )
        } returns false

        // Act
        val result = configDataSource.getFlag("test")

        // Assert
        result shouldBe false
    }

    test("getString should return the value from cache if available") {
        // Arrange
        val expectedValue = "Test Value"
        coEvery {
            cacheRepo.get(
                any(),
                any(),
                any<suspend () -> String>()
            )
        } returns expectedValue

        // Act
        val result = configDataSource.getString("test")

        // Assert
        result shouldBe expectedValue
    }

    test("getString should return the value from source if cache is null") {
        // Arrange
        val expectedValue = "Test Value"
        coEvery { source.getString(any(), any(), any()) } returns expectedValue
        coEvery {
            cacheRepo.get(
                any(),
                any(),
                any<suspend () -> String>()
            )
        } returns expectedValue

        // Act
        val result = configDataSource.getString("test")

        // Assert
        result shouldBe expectedValue
    }

    test("getInt should return the value from cache if available") {
        // Arrange
        val expectedValue = 42
        coEvery {
            cacheRepo.get(
                any(),
                any(),
                any<suspend () -> Int>()
            )
        } returns expectedValue

        // Act
        val result = configDataSource.getInt("test")

        // Assert
        result shouldBe expectedValue
    }

    test("getInt should return the value from source if cache is null") {
        // Arrange
        val expectedValue = 42
        coEvery { source.getInt(any(), any(), any()) } returns expectedValue
        coEvery {
            cacheRepo.get(
                any(),
                any(),
                any<suspend () -> Int>()
            )
        } returns expectedValue

        // Act
        val result = configDataSource.getInt("test")

        // Assert
        result shouldBe expectedValue
    }
})
