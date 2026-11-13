
package com.fahlabs.mafia.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fahlabs.mafia.core.localization.LanguageResources
import com.fahlabs.mafia.core.localization.LocalAppLanguage
import com.fahlabs.mafia.core.theme.MafiaTheme
import androidx.compose.foundation.background

@Composable
fun SetupScreen(
    onBack: () -> Unit,
    onContinue: (Int) -> Unit
) {
    val resources = LanguageResources.forLanguage(LocalAppLanguage.current)

    var playerCount by remember {
        mutableIntStateOf(6)
    }

    val colors = MafiaTheme.colors

    Column(
        modifier = Modifier
    .fillMaxSize()
    .background(MafiaTheme.colors.background)
    .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = resources.playerCount,
            color = colors.textPrimary,
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = playerCount.toString(),
            color = colors.accent,
            fontSize = 56.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            OutlinedButton(
                onClick = { if (playerCount > 4) playerCount-- },
                enabled = playerCount > 4,
                border = BorderStroke(1.dp, colors.border),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = colors.textPrimary,
                    disabledContentColor = colors.textSecondary
                )
            ) {
                Text("−", fontSize = 24.sp)
            }

            OutlinedButton(
                onClick = { if (playerCount < 12) playerCount++ },
                enabled = playerCount < 12,
                border = BorderStroke(1.dp, colors.border),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = colors.textPrimary,
                    disabledContentColor = colors.textSecondary
                )
            ) {
                Text("+", fontSize = 24.sp)
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { onContinue(playerCount) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.primary,
                contentColor = colors.onPrimary
            )
        ) {
            Text(resources.continueText)
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(1.dp, colors.border),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = colors.textPrimary
            )
        ) {
            Text(resources.back)
        }
    }
}
