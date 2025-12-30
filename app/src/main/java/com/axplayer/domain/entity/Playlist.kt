package com.axplayer.domain.entity

data class Playlist(
    val id: String,
    val name: String,
    val description: String = "",
    val videoIds: List<String> = emptyList(),
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val isSmartPlaylist: Boolean = false,
    val smartPlaylistType: SmartPlaylistType? = null,
    val thumbnailUri: String? = null,
    val isFavorite: Boolean = false
) {
    val videoCount: Int
        get() = videoIds.size
}

enum class SmartPlaylistType {
    RECENTLY_ADDED,
    RECENTLY_PLAYED,
    FAVORITES,
    UNWATCHED,
    MOST_PLAYED,
    LONG_VIDEOS,
    SHORT_VIDEOS
}

enum class LoopMode {
    NONE,
    ONE,
    ALL
}

enum class PlaybackSpeed(val value: Float, val label: String) {
    SPEED_0_25(0.25f, "0.25x"),
    SPEED_0_5(0.5f, "0.5x"),
    SPEED_0_75(0.75f, "0.75x"),
    NORMAL(1.0f, "1.0x"),
    SPEED_1_25(1.25f, "1.25x"),
    SPEED_1_5(1.5f, "1.5x"),
    SPEED_1_75(1.75f, "1.75x"),
    SPEED_2_0(2.0f, "2.0x");

    companion object {
        fun fromValue(value: Float): PlaybackSpeed {
            return entries.find { it.value == value } ?: NORMAL
        }
    }
}

enum class AspectRatio(val ratio: Float?, val label: String) {
    FIT(null, "Fit"),
    FILL(null, "Fill"),
    ZOOM(null, "Zoom"),
    RATIO_4_3(4f / 3f, "4:3"),
    RATIO_16_9(16f / 9f, "16:9"),
    RATIO_21_9(21f / 9f, "21:9"),
    CUSTOM(null, "Custom");

    companion object {
        fun fromLabel(label: String): AspectRatio {
            return entries.find { it.label == label } ?: FIT
        }
    }
}
