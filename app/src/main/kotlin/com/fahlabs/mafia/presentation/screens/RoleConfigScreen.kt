
package com.fahlabs.mafia.presentation.screens

import androidx.compose.foundation.background
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fahlabs.mafia.core.localization.LanguageResources
import com.fahlabs.mafia.core.localization.LocalAppLanguage
import com.fahlabs.mafia.core.theme.MafiaTheme
import androidx.compose.foundation.BorderStroke

@Composable
fun RoleConfigScreen(
    playerCount: Int,
    onBack: () -> Unit,
    onContinue: (Int, Int, Int) -> Unit
) {
    val resources =
    LanguageResources.forLanguage(LocalAppLanguage.current)

    val colors = MafiaTheme.colors

    var mafiaCount by remember(playerCount) {
        mutableIntStateOf(1)
    }

    var doctorCount by remember(playerCount) {
        mutableIntStateOf(1)
    }

    var detectiveCount by remember(playerCount) {
        mutableIntStateOf(1)
    }

    val villagerCount =
    playerCount - mafiaCount - doctorCount - detectiveCount

    val canContinue = villagerCount >= 1

    Column(
        modifier = Modifier
        .fillMaxSize()
        .background(colors.background)
        .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = resources.roleConfiguration,
            color = colors.textPrimary,
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        RoleCounter(
            title = resources.mafiaRole,
            count = mafiaCount,
            onDecrease = { if (mafiaCount > 1) mafiaCount-- },
            onIncrease = {
                if (mafiaCount < playerCount - doctorCount - detectiveCount - 1) {
                    mafiaCount++
                }
            }
        )

        RoleCounter(
            title = resources.doctorRole,
            count = doctorCount,
            onDecrease = { if (doctorCount > 0) doctorCount-- },
            onIncrease = {
                if (villagerCount > 1) doctorCount++
            }
        )

        RoleCounter(
            title = resources.detectiveRole,
            count = detectiveCount,
            onDecrease = { if (detectiveCount > 0) detectiveCount-- },
            onIncrease = {
                if (villagerCount > 1) detectiveCount++
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "${resources.villagerRole}: $villagerCount",
            color = colors.accent,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                onContinue(
                    mafiaCount,
                    doctorCount,
                    detectiveCount
                )
            },
            enabled = canContinue,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.primary,
                contentColor = colors.onPrimary,
                disabledContainerColor = colors.surfaceVariant,
                disabledContentColor = colors.textSecondary
            )
        ) {
            Text(resources.continueText)
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = colors.textPrimary
            )
        ) {
            Text(resources.back)
        }
    }
}

@Composable
private fun RoleCounter(
    title: String,
    count: Int,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit
) {
    val colors = MafiaTheme.colors

    Column(
        modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 12.dp)
    ) {
        Text(
            text = title,
            color = colors.textPrimary,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = onDecrease,
                border = BorderStroke(1.dp, colors.border),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = colors.textPrimary
                )
            ) {
                Text("−")
            }

            Text(
                text = count.toString(),
                color = colors.accent,
                fontSize = 22.sp
            )

            OutlinedButton(
                onClick = onIncrease,
                border = BorderStroke(1.dp, colors.border),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = colors.textPrimary
                )
            ) {
                Text("+")
            }
        }
    }
}
