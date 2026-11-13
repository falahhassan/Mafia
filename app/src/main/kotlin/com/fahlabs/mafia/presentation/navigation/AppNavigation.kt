
package com.fahlabs.mafia.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fahlabs.mafia.core.localization.LanguageResources
import com.fahlabs.mafia.core.localization.LocalAppLanguage
import com.fahlabs.mafia.core.theme.MafiaTheme
import com.fahlabs.mafia.presentation.screens.HomeScreen

@Composable
fun AppNavigation() {
    var currentScreen by remember {
        mutableStateOf(Screen.HOME)
    }

    val strings = LanguageResources.forLanguage(
        LocalAppLanguage.current
    )

    val colors = MafiaTheme.colors

    when (currentScreen) {
        Screen.HOME -> {
            HomeScreen(
                onNewGame = {
                    currentScreen = Screen.SETUP
                },
                onSettings = {
                    currentScreen = Screen.SETTINGS
                }
            )
        }

        Screen.SETUP -> {
            PlaceholderScreen(
                title = strings.newGame,
                onBack = {
                    currentScreen = Screen.HOME
                }
            )
        }

        Screen.SETTINGS -> {
            PlaceholderScreen(
                title = strings.settings,
                onBack = {
                    currentScreen = Screen.HOME
                }
            )
        }
    }
}

@Composable
private fun PlaceholderScreen(
    title: String,
    onBack: () -> Unit
) {
    val colors = MafiaTheme.colors

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            color = colors.textPrimary
        )

        Button(onClick = onBack) {
            Text("←")
        }
    }
}
