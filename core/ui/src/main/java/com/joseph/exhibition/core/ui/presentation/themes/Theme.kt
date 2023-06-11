package com.joseph.exhibition.core.ui.presentation.themes

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalInspectionMode
import com.joseph.exhibition.core.ui.presentation.themes.colors.AppColor

/**
 * Exhibition theme. This is an to allow disabling dynamic theming
 * in tests.
 *
 */
@Composable
fun MainTheme(
    content: @Composable () -> Unit
) {
    val colors = appColor()
    CompositionLocalProvider(
        LocalBackgroundTheme provides colors.getBackground()
    ) {
        MaterialTheme(
            colorScheme = colors.getColorScheme(),
            typography = Typography,
            shapes = shapes,
            content = content
        )
    }
}

@Composable
fun appColor(): AppColor {
    val isEditMode = LocalInspectionMode.current
    return remember { AppColor(isEditMode) }
}