package com.axplayer.domain.repository

import com.axplayer.domain.entity.Subtitle
import com.axplayer.domain.entity.SubtitleSettings
import kotlinx.coroutines.flow.Flow

interface SubtitleRepository {
    fun getSubtitlesForVideo(videoId: String): Flow<List<Subtitle>>
    suspend fun insertSubtitle(subtitle: Subtitle)
    suspend fun deleteSubtitle(id: String)
    suspend fun downloadSubtitle(videoId: String, language: String, videoTitle: String): Subtitle
    fun getSubtitleSettings(): Flow<SubtitleSettings>
    suspend fun updateSubtitleSettings(settings: SubtitleSettings)
    suspend fun searchSubtitles(query: String, language: String): List<Subtitle>
}
