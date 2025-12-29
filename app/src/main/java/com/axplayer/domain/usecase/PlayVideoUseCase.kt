package com.axplayer.domain.usecase

import com.axplayer.domain.entity.Video
import com.axplayer.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlayVideoUseCase @Inject constructor(
    private val repository: VideoRepository
) {
    operator fun invoke(videoId: String): Flow<Video?> {
        return repository.getVideoById(videoId)
    }
    
    suspend fun toggleFavorite(videoId: String) {
        repository.toggleFavorite(videoId)
    }
}
