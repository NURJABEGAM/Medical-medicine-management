package com.axplayer.domain.entity

data class AppSettings(
    val playbackSettings: PlaybackSettings = PlaybackSettings(),
    val displaySettings: DisplaySettings = DisplaySettings(),
    val gestureSettings: GestureSettings = GestureSettings(),
    val subtitleSettings: SubtitleSettings = SubtitleSettings(),
    val notificationSettings: NotificationSettings = NotificationSettings(),
    val privacySettings: PrivacySettings = PrivacySettings()
)

data class PlaybackSettings(
    val defaultSpeed: Float = 1.0f,
    val autoResume: Boolean = true,
    val hardwareAcceleration: Boolean = true,
    val preferredAudioLanguage: String = "en",
    val preferredSubtitleLanguage: String = "en",
    val autoLoadSubtitles: Boolean = true,
    val bufferSize: Int = 50,
    val seekPreview: Boolean = true,
    val loopMode: LoopMode = LoopMode.NONE,
    val sleepTimer: SleepTimer = SleepTimer.OFF
)

enum class SleepTimer(val minutes: Int, val label: String) {
    OFF(0, "Off"),
    FIVE(5, "5 min"),
    TEN(10, "10 min"),
    FIFTEEN(15, "15 min"),
    THIRTY(30, "30 min"),
    SIXTY(60, "60 min");

    companion object {
        fun fromMinutes(minutes: Int): SleepTimer {
            return entries.find { it.minutes == minutes } ?: OFF
        }
    }
}

data class DisplaySettings(
    val theme: Theme = Theme.DARK,
    val colorScheme: ColorScheme = ColorScheme.CYAN,
    val fontFamily: String = "sans-serif",
    val statusBarBehavior: StatusBarBehavior = StatusBarBehavior.AUTO_HIDE,
    val fullscreenMode: FullscreenMode = FullscreenMode.IMMERSIVE,
    val oledOptimization: Boolean = true,
    val dynamicTheming: Boolean = true,
    val uiLayout: UILayout = UILayout.NORMAL
)

enum class Theme {
    LIGHT,
    DARK,
    AUTO
}

enum class ColorScheme(val label: String) {
    CYAN("Cyan"),
    PURPLE("Purple"),
    RED("Red"),
    BLUE("Blue"),
    GREEN("Green"),
    ORANGE("Orange"),
    PINK("Pink");

    companion object {
        fun fromLabel(label: String): ColorScheme {
            return entries.find { it.label == label } ?: CYAN
        }
    }
}

enum class StatusBarBehavior {
    ALWAYS_SHOW,
    AUTO_HIDE,
    ALWAYS_HIDE
}

enum class FullscreenMode {
    NORMAL,
    IMMERSIVE,
    IMMERSIVE_STICKY
}

enum class UILayout {
    COMPACT,
    NORMAL,
    SPACIOUS
}

data class GestureSettings(
    val gesturesEnabled: Boolean = true,
    val gestureSensitivity: Float = 1.0f,
    val doubleTapAction: GestureAction = GestureAction.PLAY_PAUSE,
    val longPressAction: GestureAction = GestureAction.SHOW_INFO,
    val swipeAction: GestureAction = GestureAction.SEEK,
    val hapticFeedback: Boolean = true,
    val hapticIntensity: Float = 1.0f
)

enum class GestureAction {
    NONE,
    PLAY_PAUSE,
    SEEK,
    VOLUME,
    BRIGHTNESS,
    SHOW_INFO,
    SPEED_CONTROL,
    BOOKMARK
}

data class NotificationSettings(
    val downloadNotifications: Boolean = true,
    val updateNotifications: Boolean = true,
    val playbackNotifications: Boolean = true,
    val vibrationEnabled: Boolean = true,
    val vibrationIntensity: Float = 1.0f
)

data class PrivacySettings(
    val analyticsEnabled: Boolean = true,
    val crashReportingEnabled: Boolean = true,
    val clearWatchHistory: Boolean = false,
    val clearSearchHistory: Boolean = false
)
