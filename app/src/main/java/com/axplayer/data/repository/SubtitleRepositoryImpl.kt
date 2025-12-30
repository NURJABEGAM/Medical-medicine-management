package com.axplayer.data.repository

import com.axplayer.data.db.SubtitleDao
import com.axplayer.data.db.SubtitleEntity
import com.axplayer.data.preferences.PreferencesManager
import com.axplayer.domain.entity.Subtitle
import com.axplayer.domain.entity.SubtitleFormat
import com.axplayer.domain.entity.SubtitleSettings
import com.axplayer.domain.repository.SubtitleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubtitleRepositoryImpl @Inject constructor(
    private val subtitleDao: SubtitleDao,
    private val preferencesManager: PreferencesManager
) : SubtitleRepository {

    override fun getSubtitlesForVideo(videoId: String): Flow<List<Subtitle>> {
        return subtitleDao.getSubtitlesForVideo(videoId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertSubtitle(subtitle: Subtitle) {
        subtitleDao.insertSubtitle(SubtitleEntity.fromDomain(subtitle))
    }

    override suspend fun deleteSubtitle(id: String) {
        subtitleDao.deleteSubtitle(id)
    }

    override suspend fun downloadSubtitle(
        videoId: String,
        language: String,
        videoTitle: String
    ): Subtitle {
        val subtitle = Subtitle(
            id = System.currentTimeMillis().toString(),
            videoId = videoId,
            filePath = "/storage/emulated/0/Download/${videoTitle}_${language}.srt",
            language = language,
            languageCode = language.take(2),
            format = SubtitleFormat.SRT,
            isEmbedded = false,
            trackIndex = -1
        )
        insertSubtitle(subtitle)
        return subtitle
    }

    override fun getSubtitleSettings(): Flow<SubtitleSettings> {
        return preferencesManager.subtitleSettings
    }

    override suspend fun updateSubtitleSettings(settings: SubtitleSettings) {
        preferencesManager.updateSubtitleSettings(settings)
    }

    override suspend fun searchSubtitles(query: String, language: String): List<Subtitle> {
        return emptyList()
    }
}
