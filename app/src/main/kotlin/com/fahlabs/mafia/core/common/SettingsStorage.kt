
package com.fahlabs.mafia.core.common

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.fahlabs.mafia.core.localization.AppLanguage
import com.fahlabs.mafia.core.theme.ThemeId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.settingsDataStore by preferencesDataStore(
    name = "app_settings"
)

data class AppSettings(
    val language: AppLanguage = AppLanguage.ENGLISH,
    val theme: ThemeId = ThemeId.MIDNIGHT
)

object SettingsStorage {

    private val LANGUAGE_KEY = stringPreferencesKey("language")
    private val THEME_KEY = stringPreferencesKey("theme")

    fun settingsFlow(context: Context): Flow<AppSettings> {
        return context.settingsDataStore.data.map { preferences ->

            val languageCode = preferences[LANGUAGE_KEY]
            val themeName = preferences[THEME_KEY]

            val language = AppLanguage.entries.firstOrNull {
                it.code == languageCode
            } ?: AppLanguage.ENGLISH

            val theme = ThemeId.entries.firstOrNull {
                it.name == themeName
            } ?: ThemeId.MIDNIGHT

            AppSettings(
                language = language,
                theme = theme
            )
        }
    }

    suspend fun saveLanguage(
        context: Context,
        language: AppLanguage
    ) {
        context.settingsDataStore.edit { preferences ->
            preferences[LANGUAGE_KEY] = language.code
        }
    }

    suspend fun saveTheme(
        context: Context,
        theme: ThemeId
    ) {
        context.settingsDataStore.edit { preferences ->
            preferences[THEME_KEY] = theme.name
        }
    }
}
