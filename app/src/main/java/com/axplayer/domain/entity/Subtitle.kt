package com.axplayer.domain.entity

import androidx.compose.ui.graphics.Color

data class Subtitle(
    val id: String,
    val videoId: String,
    val filePath: String,
    val language: String,
    val languageCode: String,
    val format: SubtitleFormat,
    val isEmbedded: Boolean = false,
    val trackIndex: Int = -1,
    val addedAt: Long = System.currentTimeMillis()
)

enum class SubtitleFormat(val extension: String, val mimeType: String) {
    SRT("srt", "application/x-subrip"),
    VTT("vtt", "text/vtt"),
    ASS("ass", "text/x-ssa"),
    SSA("ssa", "text/x-ssa"),
    SUB("sub", "text/x-microdvd"),
    SUBRIP("srt", "application/x-subrip");

    companion object {
        fun fromExtension(extension: String): SubtitleFormat? {
            return entries.find { it.extension.equals(extension, ignoreCase = true) }
        }
    }
}

data class SubtitleSettings(
    val fontFamily: String = "sans-serif",
    val fontSize: Int = 16,
    val textColor: Long = 0xFFFFFFFF,
    val outlineColor: Long = 0xFF000000,
    val outlineWidth: Int = 2,
    val backgroundColor: Long = 0x00000000,
    val backgroundOpacity: Float = 0f,
    val position: SubtitlePosition = SubtitlePosition.BOTTOM,
    val alignment: SubtitleAlignment = SubtitleAlignment.CENTER,
    val shadowOffsetX: Float = 2f,
    val shadowOffsetY: Float = 2f,
    val shadowBlur: Float = 4f,
    val shadowColor: Long = 0x80000000,
    val lineSpacing: Float = 1.2f,
    val letterSpacing: Float = 0f,
    val timingOffset: Long = 0L
)

enum class SubtitlePosition {
    TOP,
    CENTER,
    BOTTOM
}

enum class SubtitleAlignment {
    LEFT,
    CENTER,
    RIGHT
}

data class AudioTrack(
    val index: Int,
    val language: String,
    val languageCode: String,
    val label: String,
    val isDefault: Boolean = false
)

data class VideoBookmark(
    val id: String,
    val videoId: String,
    val position: Long,
    val title: String,
    val description: String = "",
    val thumbnailUri: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
