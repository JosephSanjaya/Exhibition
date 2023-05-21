package com.joseph.exhibition.core.common.di

import android.content.SharedPreferences
import com.google.gson.Gson
import com.joseph.exhibition.core.common.data.cache.CacheRepo
import com.tencent.mmkv.MMKV
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

/**
 * A Dagger module that provides common dependencies for the app, such as shared preferences and gson.
 */
@InstallIn(SingletonComponent::class)
@Module
class CommonModules {
    /**
     * Provides a singleton instance of the default shared preferences using MMKV.
     * @return The shared preferences instance to use for general data storage.
     */
    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences {
        return MMKV.defaultMMKV(MMKV.MULTI_PROCESS_MODE, null)
    }

    /**
     * Provides a singleton instance of the named shared preferences using MMKV for caching data.
     * @return The shared preferences instance to use for caching data with CacheRepo interface.
     */
    @Provides
    @Singleton
    @Named(CacheRepo.NAMED)
    fun provideCache(): SharedPreferences {
        return MMKV.mmkvWithID(CacheRepo.NAMED, MMKV.MULTI_PROCESS_MODE)
    }

    /**
     * Provides a singleton instance of the gson object for serializing and deserializing data.
     * @return The gson instance to use for data conversion.
     */
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson().newBuilder()
            .setLenient()
            .create()
    }
}
