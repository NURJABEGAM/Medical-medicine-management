package com.axplayer.data.db

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.axplayer.domain.entity.Video

@Entity(tableName = "videos")
data class VideoEntity(
    @PrimaryKey
    val id: String,
    val uri: String,
    val title: String,
    val displayName: String,
    val duration: Long,
    val size: Long,
    val mimeType: String,
    val dateAdded: Long,
    val dateModified: Long,
    val path: String,
    val thumbnailUri: String? = null,
    val width: Int = 0,
    val height: Int = 0,
    val bitrate: Int = 0,
    val fps: Float = 0f,
    val codec: String = "",
    val audioCodec: String = "",
    val isFavorite: Boolean = false,
    val lastPlayedPosition: Long = 0L,
    val lastPlayedTime: Long = 0L,
    val playCount: Int = 0,
    val folder: String = "",
    val hasSubtitles: Boolean = false,
    val hasAudioTracks: Boolean = false
) {
    fun toDomain(): Video {
        return Video(
            id = id,
            uri = Uri.parse(uri),
            title = title,
            displayName = displayName,
            duration = duration,
            size = size,
            mimeType = mimeType,
            dateAdded = dateAdded,
            dateModified = dateModified,
            path = path,
            thumbnailUri = thumbnailUri?.let { Uri.parse(it) },
            width = width,
            height = height,
            bitrate = bitrate,
            fps = fps,
            codec = codec,
            audioCodec = audioCodec,
            isFavorite = isFavorite,
            lastPlayedPosition = lastPlayedPosition,
            lastPlayedTime = lastPlayedTime,
            playCount = playCount,
            folder = folder,
            hasSubtitles = hasSubtitles,
            hasAudioTracks = hasAudioTracks
        )
    }

    companion object {
        fun fromDomain(video: Video): VideoEntity {
            return VideoEntity(
                id = video.id,
                uri = video.uri.toString(),
                title = video.title,
                displayName = video.displayName,
                duration = video.duration,
                size = video.size,
                mimeType = video.mimeType,
                dateAdded = video.dateAdded,
                dateModified = video.dateModified,
                path = video.path,
                thumbnailUri = video.thumbnailUri?.toString(),
                width = video.width,
                height = video.height,
                bitrate = video.bitrate,
                fps = video.fps,
                codec = video.codec,
                audioCodec = video.audioCodec,
                isFavorite = video.isFavorite,
                lastPlayedPosition = video.lastPlayedPosition,
                lastPlayedTime = video.lastPlayedTime,
                playCount = video.playCount,
                folder = video.folder,
                hasSubtitles = video.hasSubtitles,
                hasAudioTracks = video.hasAudioTracks
            )
        }
    }
}
