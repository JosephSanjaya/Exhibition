package com.joseph.exhibition.core.ui.presentation.themes.colors

import android.app.Activity
import android.content.Context
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import com.blankj.utilcode.util.ActivityUtils
import com.joseph.exhibition.core.ui.presentation.di.ColorEntryPoint
import com.joseph.exhibition.core.ui.presentation.themes.BackgroundTheme
import com.joseph.exhibition.core.ui.presentation.themes.colors.variants.ColorVariant
import com.joseph.exhibition.core.ui.presentation.themes.colors.variants.ErrorColor
import com.joseph.exhibition.core.ui.presentation.themes.colors.variants.NeutralColor
import com.joseph.exhibition.core.ui.presentation.themes.colors.variants.PrimaryColor
import com.joseph.exhibition.core.ui.presentation.themes.colors.variants.SecondaryColor
import com.joseph.exhibition.core.ui.presentation.themes.colors.variants.SuccessColor
import com.joseph.exhibition.core.ui.presentation.themes.colors.variants.WarningColor
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
        operator fun invoke(isInEditMode: Boolean): AppColor {
            return if (isInEditMode) {
                AppColorImpl(
                    ErrorColor(),
                    NeutralColor(),
                    PrimaryColor(),
                    SecondaryColor(),
                    SuccessColor(),
                    WarningColor()
                )
            } else {
                val entryPoint = EntryPointAccessors.fromActivity(
                    ActivityUtils.getTopActivity(),
                    ColorEntryPoint::class.java
                )
                entryPoint.getAppColor()
            }
        }
    }
}
