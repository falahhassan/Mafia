
package com.fahlabs.mafia.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fahlabs.mafia.core.localization.AppLanguage
import com.fahlabs.mafia.core.localization.LanguageResources
import com.fahlabs.mafia.core.theme.MafiaTheme
import com.fahlabs.mafia.core.theme.ThemeId

@Composable
fun SettingsScreen(
    currentLanguage: AppLanguage,
    onLanguageChange: (AppLanguage) -> Unit,
    currentTheme: ThemeId,
    onThemeChange: (ThemeId) -> Unit,
    onBack: () -> Unit
) {
    val strings = LanguageResources.forLanguage(currentLanguage)
    val colors = MafiaTheme.colors

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(24.dp)
    ) {
        Text(
            text = strings.settings,
            style = MaterialTheme.typography.headlineMedium,
            color = colors.textPrimary
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = strings.language,
            style = MaterialTheme.typography.titleMedium,
            color = colors.primary
        )

        AppLanguage.entries.forEach { language ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onLanguageChange(language)
                    }
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = currentLanguage == language,
                    onClick = {
                        onLanguageChange(language)
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = colors.primary
                    )
                )

                Text(
                    text = language.displayName,
                    color = colors.textPrimary
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = strings.theme,
            style = MaterialTheme.typography.titleMedium,
            color = colors.primary
        )

        ThemeId.entries.forEach { theme ->
            val themeName = when (theme) {
                ThemeId.MIDNIGHT -> strings.midnight
                ThemeId.CRIMSON -> strings.crimson
                ThemeId.EMERALD -> strings.emerald
                ThemeId.VINTAGE -> strings.vintage
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onThemeChange(theme)
                    }
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = currentTheme == theme,
                    onClick = {
                        onThemeChange(theme)
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = colors.primary
                    )
                )

                Text(
                    text = themeName,
                    color = colors.textPrimary
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(strings.back)
        }
    }
}
