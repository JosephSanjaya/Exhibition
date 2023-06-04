package com.joseph.exhibition.core.ui.presentation.themes.colors.variants

import androidx.compose.ui.graphics.Color
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject
import javax.inject.Named

class WarningColor @Inject constructor() : ColorVariant {
    override val light: Color = Color(0xFFFEF6EB)
    override val lightHover: Color = Color(0xFFFEF2E0)
    override val lightActive: Color = Color(0xFFFDE4C0)
    override val normal: Color = Color(0xFFF8A933)
    override val normalHover: Color = Color(0xFFDF982E)
    override val normalActive: Color = Color(0xFFC68729)
    override val dark: Color = Color(0xFFBA7F26)
    override val darkHover: Color = Color(0xFF95651F)
    override val darkActive: Color = Color(0xFF704C17)
    override val darker: Color = Color(0xFF573B12)

    @InstallIn(ActivityComponent::class)
    @Module
    internal abstract class Binder {

        @Binds
        @Named(ColorVariant.WARNING_TAG)
        abstract fun bind(impl: WarningColor): ColorVariant
    }
}
