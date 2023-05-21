package com.joseph.exhibition.core.common.data.appwrite

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory

/**
 * An interface for creating AppWriteDbDataSource instances using assisted injection.
 */
@AssistedFactory
interface AppWriteDbFactory {
    /**
     * Creates an AppWriteDbDataSource instance with the given database and collection IDs.
     * @param databaseId The ID of the database to use.
     * @param collectionId The ID of the collection to use.
     * @return An AppWriteDbDataSource instance.
     */
    fun create(
        @Assisted("databaseId") databaseId: String,
        @Assisted("collectionId") collectionId: String,
    ): AppWriteDbDataSource
}
