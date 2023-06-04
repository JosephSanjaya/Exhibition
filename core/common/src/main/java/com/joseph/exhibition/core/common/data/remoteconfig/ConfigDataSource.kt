package com.joseph.exhibition.core.common.data.remoteconfig

import com.joseph.exhibition.core.common.BuildConfig
import com.joseph.exhibition.core.common.data.appwrite.AppWriteDbFactory
import com.joseph.exhibition.core.common.data.appwrite.AppWriteDbRepo
import com.joseph.exhibition.core.common.data.cache.CacheRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

/**
 * A data source class that implements the FlagConfigRepo interface using the AppWriteDbRepo interface.
 * @param factory The AppWriteDbFactory object that provides access to the AppWriteDbRepo instances.
 */
class ConfigDataSource @Inject constructor(
    factory: AppWriteDbFactory,
    private val cacheRepo: CacheRepo,
) : ConfigRepo {

    companion object {
        // The key for the force update value in the document
        const val FORCE_UPDATE_VAL_KEY = "value"
    }

    private val flagSource: AppWriteDbRepo by lazy {
        factory.create(
            BuildConfig.REMOTE_CONFIG_DB_ID,
            BuildConfig.REMOTE_CONFIG_FLAG_ID
        )
    }

    private val stringSource: AppWriteDbRepo by lazy {
        factory.create(
            BuildConfig.REMOTE_CONFIG_DB_ID,
            BuildConfig.REMOTE_CONFIG_STRING_ID
        )
    }

    private val intSource: AppWriteDbRepo by lazy {
        factory.create(
            BuildConfig.REMOTE_CONFIG_DB_ID,
            BuildConfig.REMOTE_CONFIG_INT_ID
        )
    }

    override suspend fun getFlag(key: String): Boolean {
        val result = cacheRepo.get(key, onCacheMiss = {
            flagSource.getBoolean(key, FORCE_UPDATE_VAL_KEY, false)
        })
        return result
    }

    override suspend fun getString(key: String): String {
        val result = cacheRepo.get(key, onCacheMiss = {
            stringSource.getString(key, FORCE_UPDATE_VAL_KEY, "")
        })
        return result
    }

    override suspend fun getInt(key: String): Int {
        val result = cacheRepo.get(key, onCacheMiss = {
            intSource.getInt(key, FORCE_UPDATE_VAL_KEY, 0)
        })
        return result
    }

    /**
     * Dagger module for binding the [ConfigDataSource] implementation to [ConfigRepo].
     */
    @InstallIn(SingletonComponent::class)
    @Module
    internal abstract class Binder {

        /**
         * Binds the implementation of [ConfigDataSource] to [ConfigRepo].
         *
         * @param impl The implementation of [ConfigDataSource] to bind.
         * @return The bound [ConfigRepo] instance.
         */
        @Binds
        abstract fun bind(impl: ConfigDataSource): ConfigRepo
    }
}
