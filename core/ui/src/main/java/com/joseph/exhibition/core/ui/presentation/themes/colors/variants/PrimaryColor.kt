package com.joseph.exhibition.core.ui.presentation.themes.colors.variants

import androidx.compose.ui.graphics.Color
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject
import javax.inject.Named

class PrimaryColor @Inject constructor() : ColorVariant {
    override val light: Color = Color(0xFFFFF1EE)
    override val lightHover: Color = Color(0xFFFDD4C9)
    override val lightActive: Color = Color(0xFFFDC0AF)
    override val normal: Color = Color(0xFFFCA38B)
    override val normalHover: Color = Color(0xFFFCA38B)
    override val normalActive: Color = Color(0xFFFB9175)
    override val dark: Color = Color(0xFFFA7552)
    override val darkHover: Color = Color(0xFFE46A4B)
    override val darkActive: Color = Color(0xFF8A402D)
    override val darker: Color = Color(0xFF693122)

    @InstallIn(ActivityComponent::class)
    @Module
    internal abstract class Binder {

        @Binds
        @Named(ColorVariant.PRIMARY_TAG)
        abstract fun bind(impl: PrimaryColor): ColorVariant
    }
}
