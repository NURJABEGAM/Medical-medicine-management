package com.axplayer.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.axplayer.domain.entity.UserPreferences
import com.axplayer.presentation.components.GlassCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBackPress: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val preferences = state.preferences

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Playback Settings",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            item {
                SettingsSwitchItem(
                    title = "Resume Playback",
                    description = "Continue from last position",
                    checked = preferences.resumePlayback,
                    onCheckedChange = { viewModel.updateResumePlayback(it) }
                )
            }

            item {
                SettingsSwitchItem(
                    title = "Auto Play Next",
                    description = "Automatically play next video",
                    checked = preferences.autoPlayNext,
                    onCheckedChange = { viewModel.updateAutoPlayNext(it) }
                )
            }

            item {
                SettingsSwitchItem(
                    title = "Hardware Acceleration",
                    description = "Use GPU for video decoding",
                    checked = preferences.hardwareAcceleration,
                    onCheckedChange = { viewModel.updateHardwareAcceleration(it) }
                )
            }

            item {
                Divider()
            }

            item {
                Text(
                    text = "Display Settings",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            item {
                SettingsSwitchItem(
                    title = "OLED Optimization",
                    description = "True black for OLED displays",
                    checked = preferences.oledOptimization,
                    onCheckedChange = { viewModel.updateOledOptimization(it) }
                )
            }

            item {
                SettingsSwitchItem(
                    title = "Keep Screen On",
                    description = "Prevent screen timeout during playback",
                    checked = preferences.keepScreenOn,
                    onCheckedChange = { viewModel.updateKeepScreenOn(it) }
                )
            }

            item {
                Divider()
            }

            item {
                Text(
                    text = "Gesture Settings",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            item {
                SettingsSwitchItem(
                    title = "Double Tap Seek",
                    description = "Seek forward/backward with double tap",
                    checked = preferences.doubleTapSeek,
                    onCheckedChange = { viewModel.updateDoubleTapSeek(it) }
                )
            }

            item {
                SettingsSwitchItem(
                    title = "Swipe Gesture",
                    description = "Adjust brightness and volume",
                    checked = preferences.swipeGesture,
                    onCheckedChange = { viewModel.updateSwipeGesture(it) }
                )
            }

            item {
                SettingsSwitchItem(
                    title = "Haptic Feedback",
                    description = "Vibrate on touch",
                    checked = preferences.hapticFeedback,
                    onCheckedChange = { viewModel.updateHapticFeedback(it) }
                )
            }

            item {
                Divider()
            }

            item {
                Text(
                    text = "Notification Settings",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            item {
                SettingsSwitchItem(
                    title = "Show Notifications",
                    description = "Display playback controls",
                    checked = preferences.showNotifications,
                    onCheckedChange = { viewModel.updateShowNotifications(it) }
                )
            }

            item {
                Divider()
            }

            item {
                Text(
                    text = "Storage",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            item {
                Button(
                    onClick = { viewModel.clearCache() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Clear Cache")
                }
            }
        }
    }
}

@Composable
fun SettingsSwitchItem(
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    GlassCard(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
    }
}
