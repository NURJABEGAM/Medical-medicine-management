package com.axplayer.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmarks WHERE videoId = :videoId ORDER BY position ASC")
    fun getBookmarksForVideo(videoId: String): Flow<List<BookmarkEntity>>

    @Query("SELECT * FROM bookmarks ORDER BY createdAt DESC")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    @Query("SELECT * FROM bookmarks WHERE id = :id")
    suspend fun getBookmarkById(id: String): BookmarkEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkEntity)

    @Update
    suspend fun updateBookmark(bookmark: BookmarkEntity)

    @Query("DELETE FROM bookmarks WHERE id = :id")
    suspend fun deleteBookmark(id: String)

    @Query("DELETE FROM bookmarks WHERE videoId = :videoId")
    suspend fun deleteBookmarksForVideo(videoId: String)

    @Query("SELECT COUNT(*) FROM bookmarks WHERE videoId = :videoId")
    suspend fun getBookmarkCountForVideo(videoId: String): Int
}
