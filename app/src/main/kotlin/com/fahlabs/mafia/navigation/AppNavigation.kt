
package com.fahlabs.mafia.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fahlabs.mafia.screens.HomeScreen
import com.fahlabs.mafia.ui.theme.NoirBackground
import com.fahlabs.mafia.screens.CreateRoomScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onCreateRoom = {
                    navController.navigate("create_room")
                },
                onJoinRoom = {
                    navController.navigate("join_room")
                },
                onSettings = {
                    navController.navigate("settings")
                }
            )
        }

        composable("create_room") {
    CreateRoomScreen(
        onBack = {
            navController.popBackStack()
        },
        onCreateRoom = { name, maxPlayers, mafiaCount ->
            // We'll implement room creation later.
        }
    )
}

        composable("join_room") {
            PlaceholderScreen("Join Room") {
                navController.popBackStack()
            }
        }

        composable("settings") {
            PlaceholderScreen("Settings") {
                navController.popBackStack()
            }
        }
    }
}

@Composable
private fun PlaceholderScreen(
    title: String,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NoirBackground)
            .systemBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onBack) {
            Text("BACK")
        }
    }
}
