
package com.fahlabs.mafia.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fahlabs.mafia.core.localization.LanguageResources
import com.fahlabs.mafia.core.localization.LocalAppLanguage
import com.fahlabs.mafia.core.theme.MafiaTheme

@Composable
fun HomeScreen(
    onNewGame: () -> Unit,
    onSettings: () -> Unit
) {
    val strings = LanguageResources.forLanguage(LocalAppLanguage.current)
    val colors = MafiaTheme.colors

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = strings.gameTitle,
            style = MaterialTheme.typography.displayLarge,
            color = colors.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = strings.tagline,
            style = MaterialTheme.typography.bodyLarge,
            color = colors.textSecondary
        )

        Spacer(modifier = Modifier.height(56.dp))

        Button(
            onClick = onNewGame,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.primary,
                contentColor = colors.onPrimary
            )
        ) {
            Text(strings.newGame)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = onSettings,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = strings.settings,
                color = colors.textPrimary
            )
        }
    }
}
