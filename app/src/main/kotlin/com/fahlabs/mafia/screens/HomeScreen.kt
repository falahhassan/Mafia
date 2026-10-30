
package com.fahlabs.mafia.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fahlabs.mafia.ui.theme.*
import androidx.compose.ui.res.stringResource
import com.fahlabs.mafia.R

@Composable
fun HomeScreen(onCreateRoom: () -> Unit,
    onJoinRoom: () -> Unit,
    onSettings: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NoirBackground)
            .systemBarsPadding()
            .padding(horizontal = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "MAFIA",
            color = PrimaryText,
            fontSize = 46.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 10.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(R.string.tagline),
            color = NoirGold,
            fontSize = 11.sp,
            letterSpacing = 2.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(64.dp))

        Button(
            onClick = onCreateRoom,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = MafiaCrimson,
                disabledContentColor = PrimaryText
            )
        ) {
            Text(stringResource(R.string.create_room), fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = onJoinRoom,
            
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            border = BorderStroke(
                1.dp,
                NoirGold.copy(alpha = 0.5f)
            ),
            colors = ButtonDefaults.outlinedButtonColors(
                disabledContentColor = NoirGold
            )
        ) {
            Text(stringResource(R.string.join_room))
        }

        Spacer(modifier = Modifier.height(20.dp))

        TextButton(
            onClick = onSettings,
           
        ) {
            Text(
                text = stringResource(R.string.settings),
                color = SecondaryText
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(R.string.version),
            color = SecondaryText,
            fontSize = 10.sp,
            letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}
