
package com.fahlabs.mafia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.fahlabs.mafia.core.common.MafiaAppProviders
import com.fahlabs.mafia.core.common.SettingsStorage
import com.fahlabs.mafia.core.localization.AppLanguage
import com.fahlabs.mafia.core.theme.ThemeId
import com.fahlabs.mafia.presentation.navigation.AppNavigation
import kotlinx.coroutines.launch
import com.fahlabs.mafia.domain.model.GameState
import com.fahlabs.mafia.domain.engine.GameEngine
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val context = LocalContext.current
            val scope = rememberCoroutineScope()

            val settings by SettingsStorage.settingsFlow(context)
            .collectAsState(
                initial = com.fahlabs.mafia.core.common.AppSettings()
            )
            var currentGameState by remember {
                mutableStateOf<GameState?>(null)
            }

            MafiaAppProviders(
                language = settings.language,
                themeId = settings.theme
            ) {
                Box(
                    modifier = Modifier.safeDrawingPadding()
                ) {
                    AppNavigation(
                        currentLanguage = settings.language,
                        onLanguageChange = { language ->
                            scope.launch {
                                SettingsStorage.saveLanguage(
                                    context,
                                    language
                                )
                            }
                        },
                        currentTheme = settings.theme,
                        onThemeChange = { theme ->
                            scope.launch {
                                SettingsStorage.saveTheme(
                                    context,
                                    theme
                                )
                            }
                        },
                        onGameConfigCreated = { config ->
                            currentGameState = GameEngine.startGame(config)
                        },
                        currentGameState = currentGameState
                    )
                }
            }
        }
    }
}
