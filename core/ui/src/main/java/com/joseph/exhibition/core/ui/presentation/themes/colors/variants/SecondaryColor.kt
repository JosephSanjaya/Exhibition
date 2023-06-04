package com.joseph.exhibition.core.ui.presentation.themes.colors.variants

import androidx.compose.ui.graphics.Color
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject
import javax.inject.Named

class SecondaryColor @Inject constructor() : ColorVariant {
    override val light: Color = Color(0xFFEAEDEF)
    override val lightHover: Color = Color(0xFFBDC8CE)
    override val lightActive: Color = Color(0xFF9DADB6)
    override val normal: Color = Color(0xFF708895)
    override val normalHover: Color = Color(0xFF547181)
    override val normalActive: Color = Color(0xFF294D61)
    override val dark: Color = Color(0xFF254658)
    override val darkHover: Color = Color(0xFF1D3745)
    override val darkActive: Color = Color(0xFF172A35)
    override val darker: Color = Color(0xFF112029)

    @InstallIn(ActivityComponent::class)
    @Module
    internal abstract class Binder {

        @Binds
        @Named(ColorVariant.SECONDARY_TAG)
        abstract fun bind(impl: SecondaryColor): ColorVariant
    }
}
