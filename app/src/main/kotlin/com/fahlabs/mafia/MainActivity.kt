
package com.fahlabs.mafia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fahlabs.mafia.navigation.AppNavigation
import com.fahlabs.mafia.ui.theme.MafiaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MafiaTheme {
                AppNavigation()
            }
        }
    }
}
