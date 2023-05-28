package com.joseph.exhibition.core.common.data.remoteconfig

import com.joseph.exhibition.core.common.BuildConfig
import com.joseph.exhibition.core.common.data.appwrite.AppWriteDbFactory
import com.joseph.exhibition.core.common.data.appwrite.AppWriteDbRepo
import com.joseph.exhibition.core.common.data.cache.CacheRepo
import javax.inject.Inject

/**
 * A data source class that implements the FlagConfigRepo interface using the AppWriteDbRepo interface.
 * @param factory The AppWriteDbFactory object that provides access to the AppWriteDbRepo instances.
 */
class FlagConfigDataSource @Inject constructor(
    factory: AppWriteDbFactory,
    private val cacheRepo: CacheRepo,
) : FlagConfigRepo {

    companion object {
        // The document ID for the force update flag
        const val FORCE_UPDATE_DOC_KEY = "is-force-update"

        // The key for the force update value in the document
        const val FORCE_UPDATE_VAL_KEY = "value"
    }

    // The AppWriteDbRepo instance to use for accessing the flag configuration collection
    private val source: AppWriteDbRepo = factory.create(
        BuildConfig.REMOTE_CONFIG_DB_ID,
        BuildConfig.REMOTE_CONFIG_FLAG_ID
    )

    /**
     * Checks if the app requires a force update by retrieving the value from the AppWriteDbRepo instance.
     * @return True if the app requires a force update, false otherwise.
     */
    override suspend fun isForceUpdate(): Boolean {
        val result = cacheRepo.get(FORCE_UPDATE_DOC_KEY, onCacheMiss = {
            source.getBoolean(FORCE_UPDATE_DOC_KEY, FORCE_UPDATE_VAL_KEY, false)
        })
        return result
    }
}
