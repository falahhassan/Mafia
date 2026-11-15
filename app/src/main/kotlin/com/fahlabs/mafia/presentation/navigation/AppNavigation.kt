
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
import com.fahlabs.mafia.presentation.screens.PlayerNamesScreen
import com.fahlabs.mafia.presentation.screens.RoleConfigScreen
import com.fahlabs.mafia.domain.model.GameConfig
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color
import com.fahlabs.mafia.domain.model.GameState
import com.fahlabs.mafia.presentation.screens.RoleRevealScreen
import androidx.compose.material3.CircularProgressIndicator


@Composable
fun AppNavigation(
    currentLanguage: AppLanguage,
    onLanguageChange: (AppLanguage) -> Unit,
    currentTheme: ThemeId,
    onThemeChange: (ThemeId) -> Unit,
    onGameConfigCreated: (GameConfig) -> Unit,
    currentGameState: GameState?

) {
    val navController = rememberNavController()

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
                    navController.navigate(
                        "${Screen.PLAYER_NAMES.route}/$playerCount"
                    )
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
        composable("${Screen.ROLE_CONFIG.route}/{playerCount}") { backStackEntry ->

            val playerCount = backStackEntry.arguments
            ?.getString("playerCount")
            ?.toIntOrNull() ?: 6

            RoleConfigScreen(
                playerCount = playerCount,
                onBack = {
                    navController.popBackStack()
                },
                onContinue = { mafia, doctor, detective ->

                    val playerNames = navController
                    .previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<ArrayList<String>>("playerNames")
                    ?.toList()
                    ?: emptyList()

                    val gameConfig = GameConfig(
                        playerNames = playerNames,
                        mafiaCount = mafia,
                        doctorCount = doctor,
                        detectiveCount = detective
                    )

                    onGameConfigCreated(gameConfig)
                    navController.navigate(Screen.ROLE_REVEAL.route)
                }
            )
        }
        composable(Screen.ROLE_REVEAL.route) {

            if (currentGameState != null) {

                RoleRevealScreen(
                    gameState = currentGameState
                )

            } else {

                Box(
                    modifier = Modifier
                    .fillMaxSize()
                    .background(MafiaTheme.colors.background),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MafiaTheme.colors.primary
                    )
                }
            }
        }
        composable("${Screen.PLAYER_NAMES.route}/{playerCount}") { backStackEntry ->

            val playerCount = backStackEntry.arguments
            ?.getString("playerCount")
            ?.toIntOrNull() ?: 6

            PlayerNamesScreen(
                playerCount = playerCount,
                onBack = {
                    navController.popBackStack()
                },
                onContinue = { playerNames ->
                    navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("playerNames", ArrayList(playerNames))

                    navController.navigate(
                        "${Screen.ROLE_CONFIG.route}/$playerCount"
                    )
                }
            )
        }
    }
}
