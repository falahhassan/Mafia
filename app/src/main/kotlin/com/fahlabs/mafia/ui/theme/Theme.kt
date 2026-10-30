
package com.fahlabs.mafia.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val MafiaColorScheme = darkColorScheme(
    primary = MafiaCrimson,
    onPrimary = PrimaryText,

    secondary = NoirGold,
    onSecondary = NoirBackground,

    background = NoirBackground,
    onBackground = PrimaryText,

    surface = NoirSurface,
    onSurface = PrimaryText,

    error = MafiaCrimson,
    onError = PrimaryText
)

@Composable
fun MafiaTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = MafiaColorScheme,
        content = content
    )
}
