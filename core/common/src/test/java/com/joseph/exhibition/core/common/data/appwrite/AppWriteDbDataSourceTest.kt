package com.joseph.exhibition.core.common.data.appwrite
import com.joseph.exhibition.core.common.utils.Logger
import io.appwrite.models.Document
import io.appwrite.services.Databases
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerifyAll
import io.mockk.confirmVerified
import io.mockk.mockk

class AppWriteDbDataSourceTest : FunSpec({

    // Mock the dependencies of the app write data source
    val db = mockk<Databases>()
    val logger = mockk<Logger>()

    // Create the app write data source instance to test
    val collectionId = "collectionId"
    val databaseId = "databaseId"
    val appWriteDbDataSource = AppWriteDbDataSource(db, logger, databaseId, collectionId)

    // Define some constants for testing
    val documentId = "documentId"
    val default = "default"
    val queries = arrayOf("query1", "query2")

    // Define a valid document object with some data
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

    beforeTest {
        // Clear all mocks before each test
        clearAllMocks()
    }

    test("getMap should return the data from the document if it exists") {
        // Call the getMap method with the documentId and queries
        coEvery {
            db.getDocument(
                databaseId,
                collectionId,
                documentId,
                queries.toList()
            )
        } returns document
        val result = appWriteDbDataSource.getMap(documentId, *queries)

        // Verify that the result is the same as the data of the document object
        result shouldBe document.data

        // Verify that no other interactions happened with the mocks
        coVerifyAll { db.getDocument(databaseId, collectionId, documentId, queries.toList()) }
        confirmVerified(db)
    }

    test("get should return the value from the data map if it exists") {
        // Mock the getMap method to return the data map of the document object for any parameters
        coEvery {
            db.getDocument(
                databaseId,
                collectionId,
                documentId,
                queries.toList()
            )
        } returns document
//        coEvery { appWriteDbDataSource.getMap(any(), any()) } returns document.data

        // Call the get method with the documentId, key and default parameters
        val result = appWriteDbDataSource.get(documentId, "key", default)

        // Verify that the result is the same as the value of the key in the data map
        result shouldBe document.data["key"] as Any

        // Verify that no other interactions happened with the mocks
        coVerifyAll { appWriteDbDataSource.getMap(documentId, *queries) }
        confirmVerified(appWriteDbDataSource)
    }

    test("get should return the default value if the key does not exist in the data map") {
        // Mock the getMap method to return an empty map for any parameters
        coEvery { appWriteDbDataSource.getMap(any(), any()) } returns emptyMap()

        // Call the get method with the documentId, key and default parameters
        val result = appWriteDbDataSource.get(documentId, "not-exist", default)

        // Verify that the result is the same as the default value
        result shouldBe default

        // Verify that no other interactions happened with the mocks
        coVerifyAll { appWriteDbDataSource.getMap(documentId, *queries) }
        confirmVerified(appWriteDbDataSource)
    }

    test("getString should return the value from the data map casted as a string if it exists and is a string") {
        // Mock the getMap method to return the data map of the document object for any parameters
        coEvery { appWriteDbDataSource.getMap(any(), any()) } returns document.data

        // Call the getString method with the documentId, key and default parameters
        val result = appWriteDbDataSource.getString(documentId, "string-key", default)

        // Verify that the result is the same as the value of the key in the data map casted as a string
        result shouldBe document.data["string-key"].toString()

        // Verify that no other interactions happened with the mocks
        coVerifyAll { appWriteDbDataSource.getMap(documentId, *queries) }
        confirmVerified(appWriteDbDataSource)
    }
})
