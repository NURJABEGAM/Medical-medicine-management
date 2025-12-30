package com.axplayer.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SubtitleDao {
    @Query("SELECT * FROM subtitles WHERE videoId = :videoId ORDER BY addedAt DESC")
    fun getSubtitlesForVideo(videoId: String): Flow<List<SubtitleEntity>>

    @Query("SELECT * FROM subtitles WHERE id = :id")
    suspend fun getSubtitleById(id: String): SubtitleEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubtitle(subtitle: SubtitleEntity)

    @Update
    suspend fun updateSubtitle(subtitle: SubtitleEntity)

    @Query("DELETE FROM subtitles WHERE id = :id")
    suspend fun deleteSubtitle(id: String)

    @Query("DELETE FROM subtitles WHERE videoId = :videoId")
    suspend fun deleteSubtitlesForVideo(videoId: String)

    @Query("SELECT * FROM subtitles ORDER BY addedAt DESC LIMIT :limit")
    fun getRecentSubtitles(limit: Int = 10): Flow<List<SubtitleEntity>>
}
