package com.joseph.exhibition.core.ui.presentation.themes.colors.variants

import androidx.compose.ui.graphics.Color
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject
import javax.inject.Named

class SuccessColor @Inject constructor() : ColorVariant {
    override val light: Color = Color(0xFFEAFEF0)
    override val lightHover: Color = Color(0xFFE0FDE8)
    override val lightActive: Color = Color(0xFFBEFACF)
    override val normal: Color = Color(0xFF2DF064)
    override val normalHover: Color = Color(0xFF29D85A)
    override val normalActive: Color = Color(0xFF24C050)
    override val dark: Color = Color(0xFF22B44B)
    override val darkHover: Color = Color(0xFF1B903C)
    override val darkActive: Color = Color(0xFF146C2D)
    override val darker: Color = Color(0xFF105423)

    @InstallIn(ActivityComponent::class)
    @Module
    internal abstract class Binder {

        @Binds
        @Named(ColorVariant.SUCCESS_TAG)
        abstract fun bind(impl: SuccessColor): ColorVariant
    }
}
