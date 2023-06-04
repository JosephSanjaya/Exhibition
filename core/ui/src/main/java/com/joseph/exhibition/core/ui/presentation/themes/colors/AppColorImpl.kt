package com.joseph.exhibition.core.ui.presentation.themes.colors

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import com.joseph.exhibition.core.ui.presentation.themes.BackgroundTheme
import com.joseph.exhibition.core.ui.presentation.themes.colors.variants.ColorVariant
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject
import javax.inject.Named

class AppColorImpl @Inject constructor(
    @Named(ColorVariant.ERROR_TAG) override val error: ColorVariant,
    @Named(ColorVariant.NEUTRAL_TAG) override val neutral: ColorVariant,
    @Named(ColorVariant.PRIMARY_TAG) override val primary: ColorVariant,
    @Named(ColorVariant.SECONDARY_TAG) override val secondary: ColorVariant,
    @Named(ColorVariant.SUCCESS_TAG) override val success: ColorVariant,
    @Named(ColorVariant.WARNING_TAG) override val warning: ColorVariant
) : AppColor {

    override fun getColorScheme(): ColorScheme {
        return lightColorScheme(
            primary = primary.normalActive,
            onPrimary = neutral.light,
            primaryContainer = primary.light,
            onPrimaryContainer = primary.lightActive,
            secondary = secondary.normalActive,
            onSecondary = neutral.light,
            secondaryContainer = secondary.light,
            onSecondaryContainer = secondary.lightActive,
            tertiary = success.normalActive,
            onTertiary = neutral.light,
            tertiaryContainer = success.light,
            onTertiaryContainer = success.lightActive,
            error = error.normalActive,
            onError = neutral.light,
            errorContainer = error.light,
            onErrorContainer = error.lightActive,
            background = neutral.light,
            onBackground = neutral.darker,
            surface = neutral.light,
            onSurface = neutral.darker,
            surfaceVariant = neutral.light,
            onSurfaceVariant = neutral.lightActive,
            outline = neutral.normalActive,
            outlineVariant = neutral.lightActive,
            inverseSurface = neutral.darkActive,
            inverseOnSurface = neutral.light
        )
    }

    override fun getBackground(): BackgroundTheme {
        return BackgroundTheme(neutral.light)
    }

    @InstallIn(ActivityComponent::class)
    @Module
    internal abstract class Binder {

        @Binds
        abstract fun bind(impl: AppColorImpl): AppColor
    }
}
