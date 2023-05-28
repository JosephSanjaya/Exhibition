package com.joseph.exhibition.core.common.di

import com.joseph.exhibition.core.common.utils.Logger
import com.joseph.exhibition.core.common.utils.LoggerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class CommonBinds {

    @Binds
    abstract fun bindLogger(impl: LoggerImpl): Logger
}