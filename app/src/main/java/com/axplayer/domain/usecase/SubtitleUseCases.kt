package com.axplayer.domain.usecase

import com.axplayer.domain.entity.Subtitle
import com.axplayer.domain.entity.SubtitleSettings
import com.axplayer.domain.repository.SubtitleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSubtitlesForVideoUseCase @Inject constructor(
    private val repository: SubtitleRepository
) {
    operator fun invoke(videoId: String): Flow<List<Subtitle>> {
        return repository.getSubtitlesForVideo(videoId)
    }
}

class AddSubtitleUseCase @Inject constructor(
    private val repository: SubtitleRepository
) {
    suspend operator fun invoke(subtitle: Subtitle): Result<Unit> {
        return try {
            repository.insertSubtitle(subtitle)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

class RemoveSubtitleUseCase @Inject constructor(
    private val repository: SubtitleRepository
) {
    suspend operator fun invoke(subtitleId: String): Result<Unit> {
        return try {
            repository.deleteSubtitle(subtitleId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

class DownloadSubtitleUseCase @Inject constructor(
    private val repository: SubtitleRepository
) {
    suspend operator fun invoke(
        videoId: String,
        language: String,
        videoTitle: String
    ): Result<Subtitle> {
        return try {
            val subtitle = repository.downloadSubtitle(videoId, language, videoTitle)
            Result.success(subtitle)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

class GetSubtitleSettingsUseCase @Inject constructor(
    private val repository: SubtitleRepository
) {
    operator fun invoke(): Flow<SubtitleSettings> {
        return repository.getSubtitleSettings()
    }
}

class UpdateSubtitleSettingsUseCase @Inject constructor(
    private val repository: SubtitleRepository
) {
    suspend operator fun invoke(settings: SubtitleSettings): Result<Unit> {
        return try {
            repository.updateSubtitleSettings(settings)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

class SearchSubtitlesUseCase @Inject constructor(
    private val repository: SubtitleRepository
) {
    suspend operator fun invoke(
        query: String,
        language: String
    ): Result<List<Subtitle>> {
        return try {
            val results = repository.searchSubtitles(query, language)
            Result.success(results)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
