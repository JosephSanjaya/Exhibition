package com.joseph.exhibition.core.common.data.appwrite

import com.joseph.exhibition.core.common.utils.Logger
import io.appwrite.models.Document
import io.appwrite.services.Databases
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.shouldBe
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

class AppWriteDbDataSourceTest : StringSpec() {
    private val databaseId = "databaseId"
    private val collectionId = "collectionId"
    private val documentId = "documentId"
    private val queries = arrayOf("query1", "query2")
    private val loggerMock = mockk<Logger>(relaxed = true)
    private val dbMock = mockk<Databases>()

    private val dataSource = AppWriteDbDataSource(dbMock, loggerMock, databaseId, collectionId)

    init {
        "getMap should return data from the database" {
            val expectedResult = mapOf(
                "key" to "value",
                "string-key" to "string",
                "boolean" to true,
                "int" to 42,
                "long" to 123456789L,
                "double" to 3.14,
                "float" to 2.71f
            )
            val document: Document<Map<String, Any>> = Document(
                id = documentId,
                collectionId = collectionId,
                databaseId = databaseId,
                updatedAt = "",
                permissions = listOf(),
                createdAt = "",
                data = mapOf(
                    "key" to "value",
                    "string-key" to "string",
                    "boolean" to true,
                    "int" to 42,
                    "long" to 123456789L,
                    "double" to 3.14,
                    "float" to 2.71f
                )
            )
            coEvery {
                dbMock.getDocument(databaseId, collectionId, documentId, queries.toList())
            } returns document

            val result = dataSource.getMap(documentId, *queries)

            result shouldBe expectedResult

            coVerify {
                dbMock.getDocument(databaseId, collectionId, documentId, queries.toList())
            }
        }

        "get should return the value from the map" {
            val expectedResult = "value"
            val mapMock = mapOf("key" to expectedResult)
            coEvery { dataSource.getMap(documentId, *queries) } returns mapMock

            val result = dataSource.get(documentId, "key", "default", *queries)

            result shouldBe expectedResult
            coVerify { dataSource.getMap(documentId, *queries) }
        }

        "getString should return the string value from the map" {
            val expectedResult = "value"
            val mapMock = mapOf("key" to expectedResult)
            coEvery { dataSource.getMap(documentId, *queries) } returns mapMock

            val result = dataSource.getString(documentId, "key", "default", *queries)

            result shouldBe expectedResult
            coVerify { dataSource.getMap(documentId, *queries) }
        }

        // Add tests for other data types (getBoolean, getInt, getLong, getDouble, getFloat)
    }

    override suspend fun beforeTest(testCase: TestCase) {
        super.beforeTest(testCase)
        clearMocks(dbMock, loggerMock)
    }
}
