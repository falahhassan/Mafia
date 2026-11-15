package com.fahlabs.mafia.presentation.screens

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
import com.fahlabs.mafia.domain.model.GameState
import com.fahlabs.mafia.domain.model.Role
import com.fahlabs.mafia.security.SecretScreenManager

@Composable
fun RoleRevealScreen(gameState: GameState) {

    val colors = MafiaTheme.colors
    val strings = LanguageResources.forLanguage(LocalAppLanguage.current)

    var currentPlayerIndex by remember(gameState) {
        mutableStateOf(0)
    }

    var roleVisible by remember(gameState) {
        mutableStateOf(false)
    }

    var isComplete by remember(gameState) {
        mutableStateOf(false)
    }

    val currentPlayer = gameState.players.getOrNull(currentPlayerIndex)

    val privateView = currentPlayer?.let {
        SecretScreenManager.getPrivatePlayerView(
            state = gameState,
            playerId = it.id
        )
    }

    val roleName = when (privateView?.role) {
        Role.MAFIA -> strings.mafiaRole
        Role.DOCTOR -> strings.doctorRole
        Role.DETECTIVE -> strings.detectiveRole
        Role.VILLAGER -> strings.villagerRole
        null -> ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (isComplete) {

            Text(
                text = strings.allRolesRevealed,
                color = colors.primary
            )

            Text(
                text = strings.roleRevealComplete,
                color = colors.textPrimary,
                modifier = Modifier.padding(top = 16.dp)
            )

        } else if (privateView != null) {

            Text(
                text = strings.passDevice,
                color = colors.textSecondary
            )

            Text(
                text = privateView.playerName,
                color = colors.primary,
                modifier = Modifier.padding(vertical = 24.dp)
            )

            if (!roleVisible) {

                Button(
                    onClick = {
                        roleVisible = true
                    }
                ) {
                    Text(strings.showRole)
                }

            } else {

                Text(
                    text = roleName,
                    color = colors.accent,
                    modifier = Modifier.padding(vertical = 24.dp)
                )

                Button(
                    onClick = {
                        roleVisible = false

                        if (currentPlayerIndex + 1 < gameState.players.size) {
                            currentPlayerIndex++
                        } else {
                            isComplete = true
                        }
                    }
                ) {
                    Text(strings.hideRole)
                }
            }
        }
    }
}