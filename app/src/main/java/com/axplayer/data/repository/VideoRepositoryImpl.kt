package com.axplayer.data.repository

import com.axplayer.data.datasource.LocalVideoDataSource
import com.axplayer.data.db.VideoDao
import com.axplayer.data.db.VideoEntity
import com.axplayer.domain.entity.Video
import com.axplayer.domain.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VideoRepositoryImpl @Inject constructor(
    private val videoDao: VideoDao,
    private val localVideoDataSource: LocalVideoDataSource
) : VideoRepository {

    override fun getAllVideos(): Flow<List<Video>> {
        return videoDao.getAllVideos().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getVideoById(id: String): Flow<Video?> {
        return videoDao.getVideoById(id).map { it?.toDomain() }
    }

    override suspend fun updateVideo(video: Video) {
        videoDao.update(VideoEntity.fromDomain(video))
    }

    override suspend fun updatePlaybackPosition(videoId: String, position: Long) {
        val currentTime = System.currentTimeMillis()
        videoDao.updatePlaybackPosition(videoId, position, currentTime)
        Timber.d("Updated playback position for video $videoId: $position")
    }

    override suspend fun toggleFavorite(videoId: String) {
        videoDao.toggleFavorite(videoId)
        Timber.d("Toggled favorite for video $videoId")
    }

    override suspend fun deleteVideo(videoId: String) {
        videoDao.deleteById(videoId)
        Timber.d("Deleted video $videoId")
    }

    override suspend fun refreshVideos() {
        try {
            val videos = localVideoDataSource.loadVideosFromDevice()
            videoDao.insertAll(videos)
            Timber.d("Refreshed ${videos.size} videos")
        } catch (e: Exception) {
            Timber.e(e, "Error refreshing videos")
            throw e
        }
    }

    override fun searchVideos(query: String): Flow<List<Video>> {
        return videoDao.searchVideos(query).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getFavoriteVideos(): Flow<List<Video>> {
        return videoDao.getFavoriteVideos().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getRecentVideos(limit: Int): Flow<List<Video>> {
        return videoDao.getRecentVideos(limit).map { entities ->
            entities.map { it.toDomain() }
        }
    }
}
