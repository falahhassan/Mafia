
package com.fahlabs.mafia.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fahlabs.mafia.core.localization.AppLanguage
import com.fahlabs.mafia.core.localization.LanguageResources
import com.fahlabs.mafia.core.theme.MafiaTheme
import com.fahlabs.mafia.core.theme.ThemeId
import com.fahlabs.mafia.presentation.screens.HomeScreen
import com.fahlabs.mafia.presentation.screens.SettingsScreen
import com.fahlabs.mafia.presentation.screens.SetupScreen

@Composable
fun AppNavigation(
    currentLanguage: AppLanguage,
    onLanguageChange: (AppLanguage) -> Unit,
    currentTheme: ThemeId,
    onThemeChange: (ThemeId) -> Unit
) {
    val navController = rememberNavController()

    val strings = LanguageResources.forLanguage(currentLanguage)

    NavHost(
        navController = navController,
        startDestination = Screen.HOME.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Screen.HOME.route) {
            HomeScreen(
                onNewGame = {
                    navController.navigate(Screen.SETUP.route)
                },
                onSettings = {
                    navController.navigate(Screen.SETTINGS.route)
                }
            )
        }

        composable(Screen.SETUP.route) {
            SetupScreen(
                onBack = {
                    navController.popBackStack()
                },
                onContinue = { playerCount ->
                    // We'll connect the player-name screen next.
                }
            )
        }

        composable(Screen.SETTINGS.route) {
            SettingsScreen(
                currentLanguage = currentLanguage,
                onLanguageChange = onLanguageChange,
                currentTheme = currentTheme,
                onThemeChange = onThemeChange,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
private fun PlaceholderScreen(
    title: String,
    backText: String,
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
            Text(backText)
        }
    }
}
