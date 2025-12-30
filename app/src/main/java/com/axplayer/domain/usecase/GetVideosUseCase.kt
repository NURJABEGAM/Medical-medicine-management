package com.axplayer.domain.usecase

import com.axplayer.domain.entity.Video
import com.axplayer.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVideosUseCase @Inject constructor(
    private val repository: VideoRepository
) {
    operator fun invoke(): Flow<List<Video>> {
        return repository.getAllVideos()
    }
    
    fun searchVideos(query: String): Flow<List<Video>> {
        return repository.searchVideos(query)
    }
    
    fun getFavorites(): Flow<List<Video>> {
        return repository.getFavoriteVideos()
    }
    
    fun getRecent(limit: Int = 10): Flow<List<Video>> {
        return repository.getRecentVideos(limit)
    }
}
