package com.axplayer.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.axplayer.domain.entity.UserPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val dataStore = context.dataStore

    private object PreferencesKeys {
        // Playback Settings
        val RESUME_PLAYBACK = booleanPreferencesKey("resume_playback")
        val AUTO_PLAY_NEXT = booleanPreferencesKey("auto_play_next")
        val DEFAULT_PLAYBACK_SPEED = floatPreferencesKey("default_playback_speed")
        val HARDWARE_ACCELERATION = booleanPreferencesKey("hardware_acceleration")
        val BACKGROUND_PLAYBACK = booleanPreferencesKey("background_playback")
        val AUTO_LOAD_SUBTITLES = booleanPreferencesKey("auto_load_subtitles")
        val PREFERRED_AUDIO_LANGUAGE = stringPreferencesKey("preferred_audio_language")
        val PREFERRED_SUBTITLE_LANGUAGE = stringPreferencesKey("preferred_subtitle_language")
        val LOOP_MODE = stringPreferencesKey("loop_mode")
        val SLEEP_TIMER = intPreferencesKey("sleep_timer")
        
        // Display Settings
        val THEME = stringPreferencesKey("theme")
        val COLOR_SCHEME = stringPreferencesKey("color_scheme")
        val OLED_OPTIMIZATION = booleanPreferencesKey("oled_optimization")
        val SUBTITLE_SIZE = stringPreferencesKey("subtitle_size")
        val KEEP_SCREEN_ON = booleanPreferencesKey("keep_screen_on")
        val DYNAMIC_THEMING = booleanPreferencesKey("dynamic_theming")
        val UI_LAYOUT = stringPreferencesKey("ui_layout")
        
        // Subtitle Settings
        val SUBTITLE_FONT_FAMILY = stringPreferencesKey("subtitle_font_family")
        val SUBTITLE_FONT_SIZE = intPreferencesKey("subtitle_font_size")
        val SUBTITLE_TEXT_COLOR = longPreferencesKey("subtitle_text_color")
        val SUBTITLE_OUTLINE_COLOR = longPreferencesKey("subtitle_outline_color")
        val SUBTITLE_BACKGROUND_COLOR = longPreferencesKey("subtitle_background_color")
        val SUBTITLE_BACKGROUND_OPACITY = floatPreferencesKey("subtitle_background_opacity")
        val SUBTITLE_POSITION = stringPreferencesKey("subtitle_position")
        val SUBTITLE_ALIGNMENT = stringPreferencesKey("subtitle_alignment")
        val SUBTITLE_LINE_SPACING = floatPreferencesKey("subtitle_line_spacing")
        val SUBTITLE_TIMING_OFFSET = longPreferencesKey("subtitle_timing_offset")
        
        // Gesture Settings
        val DOUBLE_TAP_SEEK = booleanPreferencesKey("double_tap_seek")
        val SEEK_DURATION = intPreferencesKey("seek_duration")
        val SWIPE_GESTURE = booleanPreferencesKey("swipe_gesture")
        val HAPTIC_FEEDBACK = booleanPreferencesKey("haptic_feedback")
        val HAPTIC_INTENSITY = floatPreferencesKey("haptic_intensity")
        val GESTURE_SENSITIVITY = floatPreferencesKey("gesture_sensitivity")
        
        // Notification Settings
        val SHOW_NOTIFICATIONS = booleanPreferencesKey("show_notifications")
        val NOTIFICATION_STYLE = stringPreferencesKey("notification_style")
        val VIBRATION_ENABLED = booleanPreferencesKey("vibration_enabled")
        
        // Privacy Settings
        val ANALYTICS_ENABLED = booleanPreferencesKey("analytics_enabled")
        val CRASH_REPORTING_ENABLED = booleanPreferencesKey("crash_reporting_enabled")
        
        // Storage
        val CACHE_SIZE = longPreferencesKey("cache_size")
    }

    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Timber.e(exception, "Error reading preferences")
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            UserPreferences(
                resumePlayback = preferences[PreferencesKeys.RESUME_PLAYBACK] ?: true,
                autoPlayNext = preferences[PreferencesKeys.AUTO_PLAY_NEXT] ?: false,
                defaultPlaybackSpeed = preferences[PreferencesKeys.DEFAULT_PLAYBACK_SPEED] ?: 1.0f,
                hardwareAcceleration = preferences[PreferencesKeys.HARDWARE_ACCELERATION] ?: true,
                backgroundPlayback = preferences[PreferencesKeys.BACKGROUND_PLAYBACK] ?: false,
                theme = UserPreferences.Theme.valueOf(
                    preferences[PreferencesKeys.THEME] ?: UserPreferences.Theme.SYSTEM.name
                ),
                oledOptimization = preferences[PreferencesKeys.OLED_OPTIMIZATION] ?: true,
                subtitleSize = UserPreferences.SubtitleSize.valueOf(
                    preferences[PreferencesKeys.SUBTITLE_SIZE] ?: UserPreferences.SubtitleSize.MEDIUM.name
                ),
                keepScreenOn = preferences[PreferencesKeys.KEEP_SCREEN_ON] ?: true,
                doubleTapSeek = preferences[PreferencesKeys.DOUBLE_TAP_SEEK] ?: true,
                seekDuration = preferences[PreferencesKeys.SEEK_DURATION] ?: 10,
                swipeGesture = preferences[PreferencesKeys.SWIPE_GESTURE] ?: true,
                hapticFeedback = preferences[PreferencesKeys.HAPTIC_FEEDBACK] ?: true,
                showNotifications = preferences[PreferencesKeys.SHOW_NOTIFICATIONS] ?: true,
                notificationStyle = UserPreferences.NotificationStyle.valueOf(
                    preferences[PreferencesKeys.NOTIFICATION_STYLE] 
                        ?: UserPreferences.NotificationStyle.DETAILED.name
                ),
                cacheSize = preferences[PreferencesKeys.CACHE_SIZE] ?: (500L * 1024 * 1024)
            )
        }

    suspend fun updateResumePlayback(enabled: Boolean) {
        dataStore.edit { it[PreferencesKeys.RESUME_PLAYBACK] = enabled }
    }

    suspend fun updateAutoPlayNext(enabled: Boolean) {
        dataStore.edit { it[PreferencesKeys.AUTO_PLAY_NEXT] = enabled }
    }

    suspend fun updateDefaultPlaybackSpeed(speed: Float) {
        dataStore.edit { it[PreferencesKeys.DEFAULT_PLAYBACK_SPEED] = speed }
    }

    suspend fun updateHardwareAcceleration(enabled: Boolean) {
        dataStore.edit { it[PreferencesKeys.HARDWARE_ACCELERATION] = enabled }
    }

    suspend fun updateBackgroundPlayback(enabled: Boolean) {
        dataStore.edit { it[PreferencesKeys.BACKGROUND_PLAYBACK] = enabled }
    }

    suspend fun updateTheme(theme: UserPreferences.Theme) {
        dataStore.edit { it[PreferencesKeys.THEME] = theme.name }
    }

    suspend fun updateOledOptimization(enabled: Boolean) {
        dataStore.edit { it[PreferencesKeys.OLED_OPTIMIZATION] = enabled }
    }

    suspend fun updateSubtitleSize(size: UserPreferences.SubtitleSize) {
        dataStore.edit { it[PreferencesKeys.SUBTITLE_SIZE] = size.name }
    }

    suspend fun updateKeepScreenOn(enabled: Boolean) {
        dataStore.edit { it[PreferencesKeys.KEEP_SCREEN_ON] = enabled }
    }

    suspend fun updateDoubleTapSeek(enabled: Boolean) {
        dataStore.edit { it[PreferencesKeys.DOUBLE_TAP_SEEK] = enabled }
    }

    suspend fun updateSeekDuration(duration: Int) {
        dataStore.edit { it[PreferencesKeys.SEEK_DURATION] = duration }
    }

    suspend fun updateSwipeGesture(enabled: Boolean) {
        dataStore.edit { it[PreferencesKeys.SWIPE_GESTURE] = enabled }
    }

    suspend fun updateHapticFeedback(enabled: Boolean) {
        dataStore.edit { it[PreferencesKeys.HAPTIC_FEEDBACK] = enabled }
    }

    suspend fun updateShowNotifications(enabled: Boolean) {
        dataStore.edit { it[PreferencesKeys.SHOW_NOTIFICATIONS] = enabled }
    }

    suspend fun updateNotificationStyle(style: UserPreferences.NotificationStyle) {
        dataStore.edit { it[PreferencesKeys.NOTIFICATION_STYLE] = style.name }
    }

    suspend fun updateCacheSize(size: Long) {
        dataStore.edit { it[PreferencesKeys.CACHE_SIZE] = size }
    }

    suspend fun clearPreferences() {
        dataStore.edit { it.clear() }
    }

    val subtitleSettings: Flow<com.axplayer.domain.entity.SubtitleSettings> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Timber.e(exception, "Error reading subtitle settings")
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            com.axplayer.domain.entity.SubtitleSettings(
                fontFamily = preferences[PreferencesKeys.SUBTITLE_FONT_FAMILY] ?: "sans-serif",
                fontSize = preferences[PreferencesKeys.SUBTITLE_FONT_SIZE] ?: 16,
                textColor = preferences[PreferencesKeys.SUBTITLE_TEXT_COLOR] ?: 0xFFFFFFFF,
                outlineColor = preferences[PreferencesKeys.SUBTITLE_OUTLINE_COLOR] ?: 0xFF000000,
                backgroundColor = preferences[PreferencesKeys.SUBTITLE_BACKGROUND_COLOR] ?: 0x00000000,
                backgroundOpacity = preferences[PreferencesKeys.SUBTITLE_BACKGROUND_OPACITY] ?: 0f,
                position = com.axplayer.domain.entity.SubtitlePosition.valueOf(
                    preferences[PreferencesKeys.SUBTITLE_POSITION] 
                        ?: com.axplayer.domain.entity.SubtitlePosition.BOTTOM.name
                ),
                alignment = com.axplayer.domain.entity.SubtitleAlignment.valueOf(
                    preferences[PreferencesKeys.SUBTITLE_ALIGNMENT] 
                        ?: com.axplayer.domain.entity.SubtitleAlignment.CENTER.name
                ),
                lineSpacing = preferences[PreferencesKeys.SUBTITLE_LINE_SPACING] ?: 1.2f,
                timingOffset = preferences[PreferencesKeys.SUBTITLE_TIMING_OFFSET] ?: 0L
            )
        }

    suspend fun updateSubtitleSettings(settings: com.axplayer.domain.entity.SubtitleSettings) {
        dataStore.edit { prefs ->
            prefs[PreferencesKeys.SUBTITLE_FONT_FAMILY] = settings.fontFamily
            prefs[PreferencesKeys.SUBTITLE_FONT_SIZE] = settings.fontSize
            prefs[PreferencesKeys.SUBTITLE_TEXT_COLOR] = settings.textColor
            prefs[PreferencesKeys.SUBTITLE_OUTLINE_COLOR] = settings.outlineColor
            prefs[PreferencesKeys.SUBTITLE_BACKGROUND_COLOR] = settings.backgroundColor
            prefs[PreferencesKeys.SUBTITLE_BACKGROUND_OPACITY] = settings.backgroundOpacity
            prefs[PreferencesKeys.SUBTITLE_POSITION] = settings.position.name
            prefs[PreferencesKeys.SUBTITLE_ALIGNMENT] = settings.alignment.name
            prefs[PreferencesKeys.SUBTITLE_LINE_SPACING] = settings.lineSpacing
            prefs[PreferencesKeys.SUBTITLE_TIMING_OFFSET] = settings.timingOffset
        }
    }

    suspend fun updateColorScheme(scheme: String) {
        dataStore.edit { it[PreferencesKeys.COLOR_SCHEME] = scheme }
    }

    suspend fun updateDynamicTheming(enabled: Boolean) {
        dataStore.edit { it[PreferencesKeys.DYNAMIC_THEMING] = enabled }
    }

    suspend fun updateAutoLoadSubtitles(enabled: Boolean) {
        dataStore.edit { it[PreferencesKeys.AUTO_LOAD_SUBTITLES] = enabled }
    }

    suspend fun updatePreferredAudioLanguage(language: String) {
        dataStore.edit { it[PreferencesKeys.PREFERRED_AUDIO_LANGUAGE] = language }
    }

    suspend fun updatePreferredSubtitleLanguage(language: String) {
        dataStore.edit { it[PreferencesKeys.PREFERRED_SUBTITLE_LANGUAGE] = language }
    }

    suspend fun updateLoopMode(mode: String) {
        dataStore.edit { it[PreferencesKeys.LOOP_MODE] = mode }
    }

    suspend fun updateSleepTimer(minutes: Int) {
        dataStore.edit { it[PreferencesKeys.SLEEP_TIMER] = minutes }
    }

    suspend fun updateHapticIntensity(intensity: Float) {
        dataStore.edit { it[PreferencesKeys.HAPTIC_INTENSITY] = intensity }
    }

    suspend fun updateGestureSensitivity(sensitivity: Float) {
        dataStore.edit { it[PreferencesKeys.GESTURE_SENSITIVITY] = sensitivity }
    }

    suspend fun updateAnalyticsEnabled(enabled: Boolean) {
        dataStore.edit { it[PreferencesKeys.ANALYTICS_ENABLED] = enabled }
    }

    suspend fun updateCrashReportingEnabled(enabled: Boolean) {
        dataStore.edit { it[PreferencesKeys.CRASH_REPORTING_ENABLED] = enabled }
    }
}
