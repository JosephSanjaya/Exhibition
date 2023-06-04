package com.joseph.exhibition.core.ui.presentation.themes.colors.variants

import androidx.compose.ui.graphics.Color
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject
import javax.inject.Named

class ErrorColor @Inject constructor() : ColorVariant {
    override val light: Color = Color(0xFFFEEAEB)
    override val lightHover: Color = Color(0xFFFDE0E1)
    override val lightActive: Color = Color(0xFFFABEC2)
    override val normal: Color = Color(0xFFF02D39)
    override val normalHover: Color = Color(0xFFD82933)
    override val normalActive: Color = Color(0xFFC0242E)
    override val dark: Color = Color(0xFFB4222B)
    override val darkHover: Color = Color(0xFF901B22)
    override val darkActive: Color = Color(0xFF6C141A)
    override val darker: Color = Color(0xFF541014)

    @InstallIn(ActivityComponent::class)
    @Module
    internal abstract class Binder {

        @Binds
        @Named(ColorVariant.ERROR_TAG)
        abstract fun bind(impl: ErrorColor): ColorVariant
    }
}
