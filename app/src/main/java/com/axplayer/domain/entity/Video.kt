package com.axplayer.domain.entity

import android.net.Uri

data class Video(
    val id: String,
    val uri: Uri,
    val title: String,
    val displayName: String,
    val duration: Long,
    val size: Long,
    val mimeType: String,
    val dateAdded: Long,
    val dateModified: Long,
    val path: String,
    val thumbnailUri: Uri? = null,
    val width: Int = 0,
    val height: Int = 0,
    val bitrate: Int = 0,
    val fps: Float = 0f,
    val isFavorite: Boolean = false,
    val lastPlayedPosition: Long = 0L,
    val lastPlayedTime: Long = 0L
) {
    val durationFormatted: String
        get() {
            val hours = duration / 3600000
            val minutes = (duration % 3600000) / 60000
            val seconds = (duration % 60000) / 1000
            return if (hours > 0) {
                String.format("%d:%02d:%02d", hours, minutes, seconds)
            } else {
                String.format("%d:%02d", minutes, seconds)
            }
        }

    val sizeFormatted: String
        get() {
            val kb = size / 1024.0
            val mb = kb / 1024.0
            val gb = mb / 1024.0
            return when {
                gb >= 1 -> String.format("%.2f GB", gb)
                mb >= 1 -> String.format("%.2f MB", mb)
                else -> String.format("%.2f KB", kb)
            }
        }

    val resolution: String
        get() = if (width > 0 && height > 0) {
            "${width}x${height}"
        } else {
            "Unknown"
        }

    val aspectRatio: Float
        get() = if (width > 0 && height > 0) {
            width.toFloat() / height.toFloat()
        } else {
            16f / 9f
        }

    val playbackProgress: Float
        get() = if (duration > 0) {
            (lastPlayedPosition.toFloat() / duration.toFloat()).coerceIn(0f, 1f)
        } else {
            0f
        }
}
