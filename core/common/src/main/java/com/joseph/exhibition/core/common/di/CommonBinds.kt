package com.joseph.exhibition.core.common.di

import com.joseph.exhibition.core.common.utils.Logger
import com.joseph.exhibition.core.common.utils.LoggerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * A module that provides common bindings for the application.
 */
@InstallIn(SingletonComponent::class)
@Module
abstract class CommonBinds {

    /**
     * Binds the [Logger] interface to the [LoggerImpl] implementation.
     * @param impl The [LoggerImpl] instance to be bound.
     * @return The [Logger] instance that can be injected into other classes.
     */
    @Binds
    abstract fun bindLogger(impl: LoggerImpl): Logger
}
