package com.axplayer.domain.repository

import com.axplayer.domain.entity.VideoBookmark
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {
    fun getBookmarksForVideo(videoId: String): Flow<List<VideoBookmark>>
    fun getAllBookmarks(): Flow<List<VideoBookmark>>
    suspend fun insertBookmark(bookmark: VideoBookmark)
    suspend fun updateBookmark(bookmark: VideoBookmark)
    suspend fun deleteBookmark(id: String)
}
