package com.axplayer.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.axplayer.domain.entity.Subtitle
import com.axplayer.domain.entity.SubtitleFormat

@Entity(tableName = "subtitles")
data class SubtitleEntity(
    @PrimaryKey val id: String,
    val videoId: String,
    val filePath: String,
    val language: String,
    val languageCode: String,
    val format: String,
    val isEmbedded: Boolean,
    val trackIndex: Int,
    val addedAt: Long
) {
    fun toDomain(): Subtitle {
        return Subtitle(
            id = id,
            videoId = videoId,
            filePath = filePath,
            language = language,
            languageCode = languageCode,
            format = SubtitleFormat.valueOf(format),
            isEmbedded = isEmbedded,
            trackIndex = trackIndex,
            addedAt = addedAt
        )
    }

    companion object {
        fun fromDomain(subtitle: Subtitle): SubtitleEntity {
            return SubtitleEntity(
                id = subtitle.id,
                videoId = subtitle.videoId,
                filePath = subtitle.filePath,
                language = subtitle.language,
                languageCode = subtitle.languageCode,
                format = subtitle.format.name,
                isEmbedded = subtitle.isEmbedded,
                trackIndex = subtitle.trackIndex,
                addedAt = subtitle.addedAt
            )
        }
    }
}
