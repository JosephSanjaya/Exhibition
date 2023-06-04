package com.joseph.exhibition.core.ui.presentation.di

import com.joseph.exhibition.core.ui.presentation.themes.colors.AppColor
import com.joseph.exhibition.core.ui.presentation.themes.colors.variants.ColorVariant
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

@EntryPoint
@InstallIn(ActivityComponent::class)
interface ColorEntryPoint {
    @Named(ColorVariant.ERROR_TAG)
    fun getErrorColor(): ColorVariant

    @Named(ColorVariant.NEUTRAL_TAG)
    fun getNeutralColor(): ColorVariant

    @Named(ColorVariant.PRIMARY_TAG)
    fun getPrimaryColor(): ColorVariant

    @Named(ColorVariant.SECONDARY_TAG)
    fun getSecondaryColor(): ColorVariant

    @Named(ColorVariant.SUCCESS_TAG)
    fun getSuccessColor(): ColorVariant

    @Named(ColorVariant.WARNING_TAG)
    fun getWarningColor(): ColorVariant

    fun getAppColor(): AppColor
}
