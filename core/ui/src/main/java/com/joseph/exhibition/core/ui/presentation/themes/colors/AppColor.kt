package com.joseph.exhibition.core.ui.presentation.themes.colors

import android.app.Activity
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import com.joseph.exhibition.core.ui.presentation.di.ColorEntryPoint
import com.joseph.exhibition.core.ui.presentation.themes.BackgroundTheme
import com.joseph.exhibition.core.ui.presentation.themes.colors.variants.ColorVariant
import dagger.hilt.android.EntryPointAccessors

interface AppColor {
    val error: ColorVariant
    val neutral: ColorVariant
    val primary: ColorVariant
    val secondary: ColorVariant
    val success: ColorVariant
    val warning: ColorVariant

    fun getColorScheme(): ColorScheme
    fun getBackground(): BackgroundTheme

    companion object {
        @Composable
        operator fun invoke(activity: Activity): AppColor {
            val entryPoint = EntryPointAccessors.fromActivity(
                activity,
                ColorEntryPoint::class.java
            )
            return entryPoint.getAppColor()
        }
    }
}
