package com.axplayer.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.axplayer.domain.entity.VideoBookmark

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey val id: String,
    val videoId: String,
    val position: Long,
    val title: String,
    val description: String,
    val thumbnailUri: String?,
    val createdAt: Long
) {
    fun toDomain(): VideoBookmark {
        return VideoBookmark(
            id = id,
            videoId = videoId,
            position = position,
            title = title,
            description = description,
            thumbnailUri = thumbnailUri,
            createdAt = createdAt
        )
    }

    companion object {
        fun fromDomain(bookmark: VideoBookmark): BookmarkEntity {
            return BookmarkEntity(
                id = bookmark.id,
                videoId = bookmark.videoId,
                position = bookmark.position,
                title = bookmark.title,
                description = bookmark.description,
                thumbnailUri = bookmark.thumbnailUri,
                createdAt = bookmark.createdAt
            )
        }
    }
}
