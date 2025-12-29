package com.axplayer.domain.entity

data class UserPreferences(
    // Playback Settings
    val resumePlayback: Boolean = true,
    val autoPlayNext: Boolean = false,
    val defaultPlaybackSpeed: Float = 1.0f,
    val hardwareAcceleration: Boolean = true,
    val backgroundPlayback: Boolean = false,
    
    // Display Settings
    val theme: Theme = Theme.SYSTEM,
    val oledOptimization: Boolean = true,
    val subtitleSize: SubtitleSize = SubtitleSize.MEDIUM,
    val keepScreenOn: Boolean = true,
    
    // Gesture Settings
    val doubleTapSeek: Boolean = true,
    val seekDuration: Int = 10,
    val swipeGesture: Boolean = true,
    val hapticFeedback: Boolean = true,
    
    // Notification Settings
    val showNotifications: Boolean = true,
    val notificationStyle: NotificationStyle = NotificationStyle.DETAILED,
    
    // Storage
    val cacheSize: Long = 500L * 1024 * 1024 // 500MB
) {
    enum class Theme {
        LIGHT, DARK, SYSTEM
    }

    enum class SubtitleSize(val scale: Float) {
        SMALL(0.8f),
        MEDIUM(1.0f),
        LARGE(1.2f),
        EXTRA_LARGE(1.5f)
    }

    enum class NotificationStyle {
        MINIMAL, DETAILED
    }
}
