
package com.fahlabs.mafia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import com.fahlabs.mafia.core.common.MafiaAppProviders
import com.fahlabs.mafia.presentation.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MafiaAppProviders {
                Box(
                    modifier = Modifier.safeDrawingPadding()
                ) {
                    AppNavigation()
                }
            }
        }
    }
}
