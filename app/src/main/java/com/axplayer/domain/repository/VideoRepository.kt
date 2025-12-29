package com.axplayer.domain.repository

import com.axplayer.domain.entity.Video
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    fun getAllVideos(): Flow<List<Video>>
    
    fun getVideoById(id: String): Flow<Video?>
    
    suspend fun updateVideo(video: Video)
    
    suspend fun updatePlaybackPosition(videoId: String, position: Long)
    
    suspend fun toggleFavorite(videoId: String)
    
    suspend fun deleteVideo(videoId: String)
    
    suspend fun refreshVideos()
    
    fun searchVideos(query: String): Flow<List<Video>>
    
    fun getFavoriteVideos(): Flow<List<Video>>
    
    fun getRecentVideos(limit: Int = 10): Flow<List<Video>>
}
