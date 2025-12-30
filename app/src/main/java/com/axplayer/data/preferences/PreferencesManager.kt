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
        
        // Display Settings
        val THEME = stringPreferencesKey("theme")
        val OLED_OPTIMIZATION = booleanPreferencesKey("oled_optimization")
        val SUBTITLE_SIZE = stringPreferencesKey("subtitle_size")
        val KEEP_SCREEN_ON = booleanPreferencesKey("keep_screen_on")
        
        // Gesture Settings
        val DOUBLE_TAP_SEEK = booleanPreferencesKey("double_tap_seek")
        val SEEK_DURATION = intPreferencesKey("seek_duration")
        val SWIPE_GESTURE = booleanPreferencesKey("swipe_gesture")
        val HAPTIC_FEEDBACK = booleanPreferencesKey("haptic_feedback")
        
        // Notification Settings
        val SHOW_NOTIFICATIONS = booleanPreferencesKey("show_notifications")
        val NOTIFICATION_STYLE = stringPreferencesKey("notification_style")
        
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
}
