package com.axplayer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.axplayer.data.preferences.PreferencesManager
import com.axplayer.domain.entity.UserPreferences
import com.axplayer.presentation.navigation.Navigation
import com.axplayer.presentation.theme.AXPlayerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        
        enableEdgeToEdge()
        
        setContent {
            val preferences by preferencesManager.userPreferencesFlow.collectAsState(
                initial = UserPreferences()
            )
            
            val darkTheme = when (preferences.theme) {
                UserPreferences.Theme.LIGHT -> false
                UserPreferences.Theme.DARK -> true
                UserPreferences.Theme.SYSTEM -> androidx.compose.foundation.isSystemInDarkTheme()
            }
            
            AXPlayerTheme(
                darkTheme = darkTheme,
                oledMode = preferences.oledOptimization
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Navigation(navController = navController)
                }
            }
        }
    }
}
