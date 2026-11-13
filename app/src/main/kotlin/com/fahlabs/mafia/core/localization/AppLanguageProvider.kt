
package com.fahlabs.mafia.core.localization

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

@Composable
fun AppLanguageProvider(
    language: AppLanguage,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalAppLanguage provides language,
        LocalLayoutDirection provides language.layoutDirection(),
        content = content
    )
}
