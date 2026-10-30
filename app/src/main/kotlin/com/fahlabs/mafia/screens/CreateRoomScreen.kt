
package com.fahlabs.mafia.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.fahlabs.mafia.ui.theme.*

@Composable
fun CreateRoomScreen(
    onBack: () -> Unit,
    onCreateRoom: (String, Int, Int) -> Unit
) {
    var playerName by remember { mutableStateOf("") }
    var maxPlayers by remember { mutableIntStateOf(8) }
    var mafiaCount by remember { mutableIntStateOf(2) }

    Column(
        modifier = Modifier
        .fillMaxSize()
        .background(NoirBackground)
        .systemBarsPadding()
        .padding(24.dp)
    ) {
        TextButton(onClick = onBack) {
            Text("← BACK", color = NoirGold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "CREATE ROOM",
            color = PrimaryText,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 3.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Set up your game",
            color = SecondaryText
        )

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = "YOUR PLAYER NAME",
            color = NoirGold,
            fontSize = 12.sp,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = playerName,
            onValueChange = { playerName = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = { Text("Enter your name") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = PrimaryText,
                unfocusedTextColor = PrimaryText,
                focusedBorderColor = NoirGold,
                unfocusedBorderColor = SecondaryText,
                focusedLabelColor = NoirGold,
                cursorColor = NoirGold
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "MAXIMUM PLAYERS",
            color = NoirGold,
            fontSize = 12.sp,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            (5..12).forEach { count ->
                FilterChip(
                    selected = maxPlayers == count,
                    onClick = {
                        maxPlayers = count
                        mafiaCount = minOf(mafiaCount, count / 4)
                    },
                    label = { Text("$count") }
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "MAFIA MEMBERS",
            color = NoirGold,
            fontSize = 12.sp,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            (1..(maxPlayers / 4)).forEach { count ->
                FilterChip(
                    selected = mafiaCount == count,
                    onClick = { mafiaCount = count },
                    label = { Text("$count") }
                )
            }
        }
    }
}
