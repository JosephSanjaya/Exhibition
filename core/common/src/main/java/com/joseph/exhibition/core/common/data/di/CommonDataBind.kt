package com.joseph.exhibition.core.common.data.di

import com.joseph.exhibition.core.common.data.cache.CacheDataSource
import com.joseph.exhibition.core.common.data.cache.CacheRepo
import com.joseph.exhibition.core.common.data.remoteconfig.FlagConfigDataSource
import com.joseph.exhibition.core.common.data.remoteconfig.FlagConfigRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * A Dagger module that provides bindings for common data interfaces and implementations.
 */
@InstallIn(SingletonComponent::class)
@Module
abstract class CommonDataBind {

    /**
     * Binds the FlagConfigDataSource class to the FlagConfigRepo interface for dependency injection.
     * @param dataSource The FlagConfigDataSource instance to bind.
     * @return The FlagConfigRepo instance to inject.
     */
    @Binds
    abstract fun bindRemoteConfig(dataSource: FlagConfigDataSource): FlagConfigRepo

    /**
     * Binds the CacheDataSource class to the CacheRepo interface for dependency injection.
     * @param dataSource The CacheDataSource instance to bind.
     * @return The CacheRepo instance to inject.
     */
    @Binds
    abstract fun bindCache(dataSource: CacheDataSource): CacheRepo
}
