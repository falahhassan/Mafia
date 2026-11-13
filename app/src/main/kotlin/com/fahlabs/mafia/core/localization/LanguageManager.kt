
package com.fahlabs.mafia.core.localization

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.LayoutDirection

val LocalAppLanguage = compositionLocalOf {
    AppLanguage.ENGLISH
}

fun AppLanguage.layoutDirection(): LayoutDirection {
    return when (this) {
        AppLanguage.ENGLISH -> LayoutDirection.Ltr
        AppLanguage.ARABIC -> LayoutDirection.Rtl
    }
}
