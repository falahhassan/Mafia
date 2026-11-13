
package com.fahlabs.mafia.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalAppColors = staticCompositionLocalOf {
    ThemeConfig.colorsFor(ThemeId.MIDNIGHT)
}

@Composable
fun AppTheme(
    themeId: ThemeId = ThemeId.MIDNIGHT,
    content: @Composable () -> Unit
) {
    val appColors = ThemeConfig.colorsFor(themeId)

    val materialColors = darkColorScheme(
        primary = appColors.primary,
        onPrimary = appColors.onPrimary,
        secondary = appColors.secondary,
        background = appColors.background,
        surface = appColors.surface,
        surfaceVariant = appColors.surfaceVariant,
        error = appColors.error,
        onError = appColors.onError,
        onBackground = appColors.textPrimary,
        onSurface = appColors.textPrimary,
        outline = appColors.border
    )

    CompositionLocalProvider(
        LocalAppColors provides appColors
    ) {
        MaterialTheme(
            colorScheme = materialColors,
            typography = AppTypography,
            content = content
        )
    }
}

object MafiaTheme {
    val colors: ThemeColors
        @Composable
        get() = LocalAppColors.current
}
