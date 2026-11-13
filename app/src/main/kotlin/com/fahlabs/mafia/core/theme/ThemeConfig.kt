
package com.fahlabs.mafia.core.theme

import androidx.compose.ui.graphics.Color

enum class ThemeId(val displayName: String) {
    MIDNIGHT("Midnight"),
    CRIMSON("Crimson"),
    EMERALD("Emerald"),
    VINTAGE("Vintage")
}

object ThemeConfig {

    fun colorsFor(theme: ThemeId): ThemeColors {
        return when (theme) {

            ThemeId.MIDNIGHT -> ThemeColors(
                background = Color(0xFF111315),
                surface = Color(0xFF252729),
                surfaceVariant = Color(0xFF303336),
                primary = Color(0xFFB52C35),
                secondary = Color(0xFFD6C4A0),
                accent = Color(0xFF8D2029),
                error = Color(0xFFE04444),
                textPrimary = Color(0xFFF2F0EA),
                textSecondary = Color(0xFFA5A5A5),
                border = Color(0xFF414448),
                divider = Color(0xFF383B3E),
                onPrimary = Color(0xFFFFFFFF),
                onError = Color(0xFFFFFFFF)
            )

            ThemeId.CRIMSON -> ThemeColors(
                background = Color(0xFF17121B),
                surface = Color(0xFF28202E),
                surfaceVariant = Color(0xFF362B3D),
                primary = Color(0xFFC94B59),
                secondary = Color(0xFFD6B5C7),
                accent = Color(0xFF8E3048),
                error = Color(0xFFEF5350),
                textPrimary = Color(0xFFF2EAF2),
                textSecondary = Color(0xFFB8AAB9),
                border = Color(0xFF4A3B50),
                divider = Color(0xFF3B3040),
                onPrimary = Color(0xFFFFFFFF),
                onError = Color(0xFFFFFFFF)
            )

            ThemeId.EMERALD -> ThemeColors(
                background = Color(0xFF14201E),
                surface = Color(0xFF20302B),
                surfaceVariant = Color(0xFF2D4039),
                primary = Color(0xFF78A995),
                secondary = Color(0xFFD0C7A5),
                accent = Color(0xFF416F5C),
                error = Color(0xFFE05A52),
                textPrimary = Color(0xFFE8EFE9),
                textSecondary = Color(0xFFB2C2B8),
                border = Color(0xFF40564C),
                divider = Color(0xFF34473E),
                onPrimary = Color(0xFF101A16),
                onError = Color(0xFFFFFFFF)
            )

            ThemeId.VINTAGE -> ThemeColors(
                background = Color(0xFF252019),
                surface = Color(0xFF332B21),
                surfaceVariant = Color(0xFF453A2D),
                primary = Color(0xFFC6A36B),
                secondary = Color(0xFFE0D0B3),
                accent = Color(0xFF987444),
                error = Color(0xFFD65A4A),
                textPrimary = Color(0xFFF0E4CC),
                textSecondary = Color(0xFFD0C2A9),
                border = Color(0xFF5A4A36),
                divider = Color(0xFF493D2E),
                onPrimary = Color(0xFF211B13),
                onError = Color(0xFFFFFFFF)
            )
        }
    }
}
