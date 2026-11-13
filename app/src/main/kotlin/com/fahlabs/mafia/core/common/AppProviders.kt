
package com.fahlabs.mafia.core.common

import androidx.compose.runtime.Composable
import com.fahlabs.mafia.core.localization.AppLanguage
import com.fahlabs.mafia.core.localization.AppLanguageProvider
import com.fahlabs.mafia.core.theme.AppTheme
import com.fahlabs.mafia.core.theme.ThemeId

@Composable
fun MafiaAppProviders(
    language: AppLanguage = AppLanguage.ENGLISH,
    themeId: ThemeId = ThemeId.MIDNIGHT,
    content: @Composable () -> Unit
) {
    AppLanguageProvider(language) {
        AppTheme(themeId = themeId) {
            content()
        }
    }
}
