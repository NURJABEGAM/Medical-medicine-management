package com.axplayer.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axplayer.data.preferences.PreferencesManager
import com.axplayer.domain.entity.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state: StateFlow<SettingsState> = _state.asStateFlow()

    init {
        loadPreferences()
    }

    private fun loadPreferences() {
        viewModelScope.launch {
            preferencesManager.userPreferencesFlow.collect { preferences ->
                _state.update { it.copy(preferences = preferences) }
            }
        }
    }

    fun updateResumePlayback(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.updateResumePlayback(enabled)
        }
    }

    fun updateAutoPlayNext(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.updateAutoPlayNext(enabled)
        }
    }

    fun updateDefaultPlaybackSpeed(speed: Float) {
        viewModelScope.launch {
            preferencesManager.updateDefaultPlaybackSpeed(speed)
        }
    }

    fun updateHardwareAcceleration(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.updateHardwareAcceleration(enabled)
        }
    }

    fun updateBackgroundPlayback(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.updateBackgroundPlayback(enabled)
        }
    }

    fun updateTheme(theme: UserPreferences.Theme) {
        viewModelScope.launch {
            preferencesManager.updateTheme(theme)
        }
    }

    fun updateOledOptimization(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.updateOledOptimization(enabled)
        }
    }

    fun updateSubtitleSize(size: UserPreferences.SubtitleSize) {
        viewModelScope.launch {
            preferencesManager.updateSubtitleSize(size)
        }
    }

    fun updateKeepScreenOn(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.updateKeepScreenOn(enabled)
        }
    }

    fun updateDoubleTapSeek(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.updateDoubleTapSeek(enabled)
        }
    }

    fun updateSeekDuration(duration: Int) {
        viewModelScope.launch {
            preferencesManager.updateSeekDuration(duration)
        }
    }

    fun updateSwipeGesture(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.updateSwipeGesture(enabled)
        }
    }

    fun updateHapticFeedback(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.updateHapticFeedback(enabled)
        }
    }

    fun updateShowNotifications(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.updateShowNotifications(enabled)
        }
    }

    fun updateNotificationStyle(style: UserPreferences.NotificationStyle) {
        viewModelScope.launch {
            preferencesManager.updateNotificationStyle(style)
        }
    }

    fun clearCache() {
        viewModelScope.launch {
            // Implement cache clearing logic
        }
    }
}
