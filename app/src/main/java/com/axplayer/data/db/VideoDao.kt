package com.axplayer.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {
    @Query("SELECT * FROM videos ORDER BY dateModified DESC")
    fun getAllVideos(): Flow<List<VideoEntity>>
    
    @Query("SELECT * FROM videos WHERE id = :id")
    fun getVideoById(id: String): Flow<VideoEntity?>
    
    @Query("SELECT * FROM videos WHERE isFavorite = 1 ORDER BY dateModified DESC")
    fun getFavoriteVideos(): Flow<List<VideoEntity>>
    
    @Query("SELECT * FROM videos WHERE lastPlayedTime > 0 ORDER BY lastPlayedTime DESC LIMIT :limit")
    fun getRecentVideos(limit: Int): Flow<List<VideoEntity>>
    
    @Query("SELECT * FROM videos WHERE title LIKE '%' || :query || '%' OR displayName LIKE '%' || :query || '%' ORDER BY dateModified DESC")
    fun searchVideos(query: String): Flow<List<VideoEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(video: VideoEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(videos: List<VideoEntity>)
    
    @Update
    suspend fun update(video: VideoEntity)
    
    @Delete
    suspend fun delete(video: VideoEntity)
    
    @Query("DELETE FROM videos WHERE id = :id")
    suspend fun deleteById(id: String)
    
    @Query("UPDATE videos SET lastPlayedPosition = :position, lastPlayedTime = :time WHERE id = :id")
    suspend fun updatePlaybackPosition(id: String, position: Long, time: Long)
    
    @Query("UPDATE videos SET isFavorite = NOT isFavorite WHERE id = :id")
    suspend fun toggleFavorite(id: String)
    
    @Query("DELETE FROM videos")
    suspend fun deleteAll()
}
