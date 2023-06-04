package com.joseph.exhibition.core.ui.presentation.themes

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
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
    val colors = AppColor(LocalContext.current as Activity)
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
