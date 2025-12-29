package com.axplayer.domain.entity

data class PlaybackState(
    val isPlaying: Boolean = false,
    val currentPosition: Long = 0L,
    val duration: Long = 0L,
    val bufferedPosition: Long = 0L,
    val playbackSpeed: Float = 1.0f,
    val isFullscreen: Boolean = false,
    val isControlsVisible: Boolean = true,
    val volume: Float = 1.0f,
    val brightness: Float = 0.5f,
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val progress: Float
        get() = if (duration > 0) {
            (currentPosition.toFloat() / duration.toFloat()).coerceIn(0f, 1f)
        } else {
            0f
        }

    val bufferedProgress: Float
        get() = if (duration > 0) {
            (bufferedPosition.toFloat() / duration.toFloat()).coerceIn(0f, 1f)
        } else {
            0f
        }

    val currentPositionFormatted: String
        get() = formatTime(currentPosition)

    val durationFormatted: String
        get() = formatTime(duration)

    val remainingTimeFormatted: String
        get() = formatTime(duration - currentPosition)

    private fun formatTime(timeMs: Long): String {
        val totalSeconds = timeMs / 1000
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60
        
        return if (hours > 0) {
            String.format("%d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format("%d:%02d", minutes, seconds)
        }
    }
}

enum class PlaybackSpeed(val value: Float, val label: String) {
    SPEED_0_25(0.25f, "0.25x"),
    SPEED_0_5(0.5f, "0.5x"),
    SPEED_0_75(0.75f, "0.75x"),
    SPEED_1_0(1.0f, "1.0x"),
    SPEED_1_25(1.25f, "1.25x"),
    SPEED_1_5(1.5f, "1.5x"),
    SPEED_1_75(1.75f, "1.75x"),
    SPEED_2_0(2.0f, "2.0x");

    companion object {
        fun fromValue(value: Float): PlaybackSpeed {
            return entries.find { it.value == value } ?: SPEED_1_0
        }
    }
}
