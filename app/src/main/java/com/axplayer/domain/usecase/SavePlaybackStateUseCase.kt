package com.axplayer.domain.usecase

import com.axplayer.domain.repository.VideoRepository
import javax.inject.Inject

class SavePlaybackStateUseCase @Inject constructor(
    private val repository: VideoRepository
) {
    suspend operator fun invoke(videoId: String, position: Long) {
        repository.updatePlaybackPosition(videoId, position)
    }
}
