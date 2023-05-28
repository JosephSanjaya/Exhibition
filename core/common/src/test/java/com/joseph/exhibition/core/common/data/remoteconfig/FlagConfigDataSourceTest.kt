package com.joseph.exhibition.core.common.data.remoteconfig

import com.joseph.exhibition.core.common.data.appwrite.AppWriteDbFactory
import com.joseph.exhibition.core.common.data.appwrite.AppWriteDbRepo
import com.joseph.exhibition.core.common.data.cache.CacheRepo
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import org.mockito.kotlin.any

class FlagConfigDataSourceTest : FunSpec({

    // Create mock instances of the dependencies
    val factory = mockk<AppWriteDbFactory>()
    val cacheRepo = mockk<CacheRepo>()
    val source = mockk<AppWriteDbRepo>()

    // Create an instance of FlagConfigDataSource with the mock dependencies
    val flagConfigDataSource by lazy {
        FlagConfigDataSource(factory, cacheRepo)
    }

    beforeTest {
        // Stub the factory to return the mock source
        coEvery { factory.create(any(), any()) } returns any()
    }

    test("isForceUpdate should return true if the cache and source have true value") {
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
        val result = flagConfigDataSource.isForceUpdate()

        // Assert
        result shouldBe true
    }

    test("isForceUpdate should return false if the cache and source have false value") {
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
        val result = flagConfigDataSource.isForceUpdate()

        // Assert
        result shouldBe false
    }

    test("isForceUpdate should return true if the cache has null value and source has true value") {
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
        val result = flagConfigDataSource.isForceUpdate()

        // Assert
        result shouldBe true
    }

    test("isForceUpdate should return false if the cache has null value and source has false value") {
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
        val result = flagConfigDataSource.isForceUpdate()

        // Assert
        result shouldBe false
    }
})
