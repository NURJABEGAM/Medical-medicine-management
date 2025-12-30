package com.axplayer.domain.entity

data class VideoFilter(
    val brightness: Float = 1.0f,
    val contrast: Float = 1.0f,
    val saturation: Float = 1.0f,
    val hue: Float = 0f
) {
    companion object {
        val DEFAULT = VideoFilter()
        
        fun reset() = DEFAULT
    }
    
    fun isDefault(): Boolean {
        return brightness == 1.0f && 
               contrast == 1.0f && 
               saturation == 1.0f && 
               hue == 0f
    }
}

data class EqualizerSettings(
    val isEnabled: Boolean = false,
    val preset: EqualizerPreset = EqualizerPreset.NORMAL,
    val bands: List<Float> = EqualizerPreset.NORMAL.values
)

enum class EqualizerPreset(val label: String, val values: List<Float>) {
    NORMAL("Normal", listOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)),
    BASS("Bass Boost", listOf(8f, 6f, 4f, 2f, 0f, -2f, -4f, -6f, -8f, -8f)),
    TREBLE("Treble Boost", listOf(-8f, -8f, -6f, -4f, -2f, 0f, 2f, 4f, 6f, 8f)),
    VOCAL("Vocal", listOf(-4f, -2f, 0f, 2f, 4f, 4f, 2f, 0f, -2f, -4f)),
    ROCK("Rock", listOf(6f, 4f, 2f, 0f, -2f, -2f, 0f, 2f, 4f, 6f)),
    JAZZ("Jazz", listOf(4f, 2f, 0f, 2f, 4f, 4f, 2f, 0f, 2f, 4f)),
    CLASSICAL("Classical", listOf(6f, 4f, 2f, 0f, 0f, 0f, 2f, 4f, 6f, 8f)),
    POP("Pop", listOf(-2f, 0f, 2f, 4f, 6f, 6f, 4f, 2f, 0f, -2f)),
    FLAT("Flat", listOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f));

    companion object {
        fun fromLabel(label: String): EqualizerPreset {
            return entries.find { it.label == label } ?: NORMAL
        }
    }
}

enum class SortOrder {
    NAME_ASC,
    NAME_DESC,
    DATE_ADDED_ASC,
    DATE_ADDED_DESC,
    SIZE_ASC,
    SIZE_DESC,
    DURATION_ASC,
    DURATION_DESC
}

enum class FilterType {
    ALL,
    FAVORITES,
    RECENTLY_PLAYED,
    UNWATCHED,
    LONG_VIDEOS,
    SHORT_VIDEOS
}

data class LibraryFilter(
    val searchQuery: String = "",
    val sortOrder: SortOrder = SortOrder.DATE_ADDED_DESC,
    val filterType: FilterType = FilterType.ALL,
    val minDuration: Long? = null,
    val maxDuration: Long? = null,
    val minSize: Long? = null,
    val maxSize: Long? = null,
    val dateRangeStart: Long? = null,
    val dateRangeEnd: Long? = null
)
