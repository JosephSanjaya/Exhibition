package com.joseph.exhibition.core.ui.presentation.themes.colors.variants

import androidx.compose.ui.graphics.Color
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject
import javax.inject.Named

class NeutralColor @Inject constructor() : ColorVariant {
    override val light: Color = Color(0xFFFFFFFF)
    override val lightHover: Color = Color(0xFFCCCCCC)
    override val lightActive: Color = Color(0xFFB4B4B4)
    override val normal: Color = Color(0xFF929292)
    override val normalHover: Color = Color(0xFF7D7D7D)
    override val normalActive: Color = Color(0xFF5C5C5C)
    override val dark: Color = Color(0xFF545454)
    override val darkHover: Color = Color(0xFF414141)
    override val darkActive: Color = Color(0xFF333333)
    override val darker: Color = Color(0xFF111111)

    @InstallIn(ActivityComponent::class)
    @Module
    internal abstract class Binder {

        @Binds
        @Named(ColorVariant.NEUTRAL_TAG)
        abstract fun bind(impl: NeutralColor): ColorVariant
    }
}
