package com.axplayer.data.repository

import com.axplayer.data.db.PlaylistDao
import com.axplayer.data.db.PlaylistEntity
import com.axplayer.data.db.VideoDao
import com.axplayer.domain.entity.Playlist
import com.axplayer.domain.entity.SmartPlaylistType
import com.axplayer.domain.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlaylistRepositoryImpl @Inject constructor(
    private val playlistDao: PlaylistDao,
    private val videoDao: VideoDao
) : PlaylistRepository {

    override fun getPlaylists(): Flow<List<Playlist>> {
        return playlistDao.getAllPlaylists().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getPlaylistById(id: String): Playlist? {
        return playlistDao.getPlaylistById(id)?.toDomain()
    }

    override suspend fun insertPlaylist(playlist: Playlist) {
        playlistDao.insertPlaylist(PlaylistEntity.fromDomain(playlist))
    }

    override suspend fun updatePlaylist(playlist: Playlist) {
        playlistDao.updatePlaylist(PlaylistEntity.fromDomain(playlist))
    }

    override suspend fun deletePlaylist(id: String) {
        playlistDao.deletePlaylist(id)
    }

    override suspend fun getSmartPlaylistVideos(type: SmartPlaylistType): List<String> {
        val allVideos = videoDao.getAllVideosSnapshot()
        return when (type) {
            SmartPlaylistType.RECENTLY_ADDED -> {
                allVideos.map { it.id }
            }
            SmartPlaylistType.RECENTLY_PLAYED -> {
                allVideos
                    .filter { it.lastPlayedTime > 0 }
                    .sortedByDescending { it.lastPlayedTime }
                    .map { it.id }
            }
            SmartPlaylistType.FAVORITES -> {
                allVideos
                    .filter { it.isFavorite }
                    .map { it.id }
            }
            SmartPlaylistType.UNWATCHED -> {
                allVideos
                    .filter { it.lastPlayedPosition == 0L }
                    .map { it.id }
            }
            SmartPlaylistType.MOST_PLAYED -> {
                allVideos
                    .sortedByDescending { it.playCount }
                    .map { it.id }
            }
            SmartPlaylistType.LONG_VIDEOS -> {
                allVideos
                    .filter { it.duration > 3600000 }
                    .map { it.id }
            }
            SmartPlaylistType.SHORT_VIDEOS -> {
                allVideos
                    .filter { it.duration < 600000 }
                    .map { it.id }
            }
        }
    }

    override suspend fun exportPlaylist(playlistId: String, format: String): String {
        val playlist = getPlaylistById(playlistId) ?: return ""
        val videos = videoDao.getAllVideosSnapshot().filter { it.id in playlist.videoIds }
        
        return when (format.lowercase()) {
            "m3u", "m3u8" -> {
                buildString {
                    appendLine("#EXTM3U")
                    appendLine("#PLAYLIST:${playlist.name}")
                    videos.forEach { video ->
                        appendLine("#EXTINF:${video.duration / 1000},${video.displayName}")
                        appendLine(video.path)
                    }
                }
            }
            else -> ""
        }
    }

    override suspend fun importPlaylist(content: String, format: String): Playlist {
        val lines = content.lines().filter { it.isNotBlank() }
        val name = lines.firstOrNull { it.startsWith("#PLAYLIST:") }
            ?.substringAfter("#PLAYLIST:")
            ?: "Imported Playlist"
        
        val videoPaths = lines.filter { !it.startsWith("#") }
        val videos = videoDao.getAllVideosSnapshot()
        val videoIds = videoPaths.mapNotNull { path ->
            videos.find { it.path == path }?.id
        }
        
        val playlist = Playlist(
            id = System.currentTimeMillis().toString(),
            name = name,
            videoIds = videoIds
        )
        
        insertPlaylist(playlist)
        return playlist
    }
}
