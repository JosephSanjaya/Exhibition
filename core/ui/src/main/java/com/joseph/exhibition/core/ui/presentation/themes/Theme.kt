package com.joseph.exhibition.core.ui.presentation.themes

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
    val colors = AppColor()
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
