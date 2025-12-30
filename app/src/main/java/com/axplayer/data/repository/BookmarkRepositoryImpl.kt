package com.axplayer.data.repository

import com.axplayer.data.db.BookmarkDao
import com.axplayer.data.db.BookmarkEntity
import com.axplayer.domain.entity.VideoBookmark
import com.axplayer.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao
) : BookmarkRepository {

    override fun getBookmarksForVideo(videoId: String): Flow<List<VideoBookmark>> {
        return bookmarkDao.getBookmarksForVideo(videoId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getAllBookmarks(): Flow<List<VideoBookmark>> {
        return bookmarkDao.getAllBookmarks().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertBookmark(bookmark: VideoBookmark) {
        bookmarkDao.insertBookmark(BookmarkEntity.fromDomain(bookmark))
    }

    override suspend fun updateBookmark(bookmark: VideoBookmark) {
        bookmarkDao.updateBookmark(BookmarkEntity.fromDomain(bookmark))
    }

    override suspend fun deleteBookmark(id: String) {
        bookmarkDao.deleteBookmark(id)
    }
}
