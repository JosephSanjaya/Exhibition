package com.joseph.exhibition.core.ui.presentation.themes.colors.variants

import androidx.compose.ui.graphics.Color

interface ColorVariant {
    val light: Color
    val lightHover: Color
    val lightActive: Color
    val normal: Color
    val normalHover: Color
    val normalActive: Color
    val dark: Color
    val darkHover: Color
    val darkActive: Color
    val darker: Color

    companion object {
        const val ERROR_TAG = "ERROR"
        const val NEUTRAL_TAG = "NEUTRAL"
        const val PRIMARY_TAG = "PRIMARY"
        const val SECONDARY_TAG = "SECONDARY"
        const val SUCCESS_TAG = "SUCCESS"
        const val WARNING_TAG = "WARNING"
    }
}
