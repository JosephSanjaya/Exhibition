package com.joseph.exhibition.core.ui.presentation.themes

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.VisibleForTesting
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

/**
 * Light default theme color scheme
 */
@VisibleForTesting
val LightDefaultColorScheme = lightColorScheme(
    primary = Blue05,
    onPrimary = White,
    primaryContainer = Blue00,
    onPrimaryContainer = Blue01,
    secondary = Yellow03,
    onSecondary = White,
    secondaryContainer = Yellow04,
    onSecondaryContainer = Yellow01,
    tertiary = Green03,
    onTertiary = Color.White,
    tertiaryContainer = Green04,
    onTertiaryContainer = Green01,
    error = Red03,
    onError = White,
    errorContainer = Red04,
    onErrorContainer = Red01,
    background = White,
    onBackground = Black04,
    surface = White,
    onSurface = Black04,
    surfaceVariant = Grey01,
    onSurfaceVariant = Black03,
    outline = Blue05,
    outlineVariant = Grey04,
    inverseSurface = Black03,
    inverseOnSurface = Grey02
)

/**
 * Dark default theme color scheme
 */
@VisibleForTesting
val DarkDefaultColorScheme = darkColorScheme(
    primary = Blue05,
    onPrimary = White,
    primaryContainer = Blue00,
    onPrimaryContainer = Blue01,
    secondary = Yellow03,
    onSecondary = White,
    secondaryContainer = Yellow04,
    onSecondaryContainer = Yellow01,
    tertiary = Green03,
    onTertiary = Color.White,
    tertiaryContainer = Green04,
    onTertiaryContainer = Green01,
    error = Red03,
    onError = White,
    errorContainer = Red04,
    onErrorContainer = Red01,
    background = White,
    onBackground = Black04,
    surface = White,
    onSurface = Black04,
    surfaceVariant = Grey01,
    onSurfaceVariant = Black03,
    outline = Blue05,
    outlineVariant = Grey04,
    inverseSurface = Black03,
    inverseOnSurface = Grey02
)

/**
 * Light Android theme color scheme
 */
@VisibleForTesting
val LightAndroidColorScheme = lightColorScheme(
    primary = Blue05,
    onPrimary = White,
    primaryContainer = Blue00,
    onPrimaryContainer = Blue01,
    secondary = Yellow03,
    onSecondary = White,
    secondaryContainer = Yellow04,
    onSecondaryContainer = Yellow01,
    tertiary = Green03,
    onTertiary = Color.White,
    tertiaryContainer = Green04,
    onTertiaryContainer = Green01,
    error = Red03,
    onError = White,
    errorContainer = Red01,
    onErrorContainer = Red04,
    background = White,
    onBackground = Black04,
    surface = White,
    onSurface = Black04,
    surfaceVariant = Grey01,
    onSurfaceVariant = Black03,
    outline = Blue05,
    outlineVariant = Grey04,
    inverseSurface = Black03,
    inverseOnSurface = Grey02
)

/**
 * Dark Android theme color scheme
 */
@VisibleForTesting
val DarkAndroidColorScheme = darkColorScheme(
    primary = Blue05,
    onPrimary = White,
    primaryContainer = Blue00,
    onPrimaryContainer = Blue01,
    secondary = Yellow03,
    onSecondary = White,
    secondaryContainer = Yellow04,
    onSecondaryContainer = Yellow01,
    tertiary = Green03,
    onTertiary = Color.White,
    tertiaryContainer = Green04,
    onTertiaryContainer = Green01,
    error = Red03,
    onError = White,
    errorContainer = Red04,
    onErrorContainer = Red01,
    background = White,
    onBackground = Black04,
    surface = White,
    onSurface = Black04,
    surfaceVariant = Grey01,
    onSurfaceVariant = Black03,
    outline = Blue05,
    outlineVariant = Grey04,
    inverseSurface = Black03,
    inverseOnSurface = Grey02
)

/**
 * Light default gradient colors
 */
val LightDefaultGradientColors = GradientColors(
    primary = Blue05,
    secondary = Yellow03,
    tertiary = Green03,
    neutral = Grey02
)

/**
 * Light Android background theme
 */
val LightAndroidBackgroundTheme = BackgroundTheme(color = White)

/**
 * Dark Android background theme
 */
val DarkAndroidBackgroundTheme = BackgroundTheme(color = White)

/**
 * AwanTunai Android theme.
 *
 * @param darkTheme Whether the theme should use a dark color scheme (follows system by default).
 * @param androidTheme Whether the theme should use the Android theme color scheme instead of the
 *        default theme. If this is `false`, then dynamic theming will be used when supported.
 */
@Composable
fun MainTheme(
    // TODO: replace with this if dark mode is necessary
    // darkTheme: Boolean = isSystemInDarkTheme(),
    darkTheme: Boolean = false,
    androidTheme: Boolean = true,
    content: @Composable () -> Unit
) = MainTheme(
    darkTheme = darkTheme,
    androidTheme = androidTheme,
    disableDynamicTheming = false,
    content = content
)

/**
 * AwanTunai theme. This is an internal only version, to allow disabling dynamic theming
 * in tests.
 *
 * @param darkTheme Whether the theme should use a dark color scheme (follows system by default).
 * @param androidTheme Whether the theme should use the Android theme color scheme instead of the
 *        default theme.
 * @param disableDynamicTheming If `true`, disables the use of dynamic theming, even when it is
 *        supported. This parameter has no effect if [androidTheme] is `true`.
 */
@Composable
internal fun MainTheme(
    // TODO: replace with this if dark mode is necessary
    // darkTheme: Boolean = isSystemInDarkTheme(),
    darkTheme: Boolean = false,
    androidTheme: Boolean = true,
    disableDynamicTheming: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (androidTheme) {
        if (darkTheme) DarkAndroidColorScheme else LightAndroidColorScheme
    } else if (!disableDynamicTheming && supportsDynamicTheming()) {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else {
        if (darkTheme) DarkDefaultColorScheme else LightDefaultColorScheme
    }

    val defaultGradientColors = GradientColors()
    val gradientColors = if (androidTheme || (!disableDynamicTheming && supportsDynamicTheming())) {
        defaultGradientColors
    } else {
        if (darkTheme) defaultGradientColors else LightDefaultGradientColors
    }

    val defaultBackgroundTheme = BackgroundTheme(
        color = colorScheme.surface,
        tonalElevation = 2.dp
    )
    val backgroundTheme = if (androidTheme) {
        if (darkTheme) DarkAndroidBackgroundTheme else LightAndroidBackgroundTheme
    } else {
        defaultBackgroundTheme
    }

    CompositionLocalProvider(
        LocalGradientColors provides gradientColors,
        LocalBackgroundTheme provides backgroundTheme
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AwanTypography,
            shapes = shapes,
            content = content
        )
    }
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
private fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
