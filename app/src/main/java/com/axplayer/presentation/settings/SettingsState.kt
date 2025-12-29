package com.axplayer.presentation.settings

import com.axplayer.domain.entity.UserPreferences

data class SettingsState(
    val preferences: UserPreferences = UserPreferences(),
    val isLoading: Boolean = false
)
