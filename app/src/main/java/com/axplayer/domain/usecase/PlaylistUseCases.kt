package com.axplayer.domain.usecase

import com.axplayer.domain.entity.Playlist
import com.axplayer.domain.entity.SmartPlaylistType
import com.axplayer.domain.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlaylistsUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    operator fun invoke(): Flow<List<Playlist>> {
        return repository.getPlaylists()
    }
}

class GetPlaylistByIdUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    suspend operator fun invoke(playlistId: String): Playlist? {
        return repository.getPlaylistById(playlistId)
    }
}

class CreatePlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    suspend operator fun invoke(name: String, description: String = ""): Result<Playlist> {
        return try {
            val playlist = Playlist(
                id = System.currentTimeMillis().toString(),
                name = name,
                description = description
            )
            repository.insertPlaylist(playlist)
            Result.success(playlist)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

class UpdatePlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    suspend operator fun invoke(playlist: Playlist): Result<Unit> {
        return try {
            repository.updatePlaylist(playlist)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

class DeletePlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    suspend operator fun invoke(playlistId: String): Result<Unit> {
        return try {
            repository.deletePlaylist(playlistId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

class AddVideoToPlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    suspend operator fun invoke(playlistId: String, videoId: String): Result<Unit> {
        return try {
            val playlist = repository.getPlaylistById(playlistId)
            if (playlist != null) {
                val updatedVideoIds = playlist.videoIds.toMutableList().apply {
                    if (!contains(videoId)) {
                        add(videoId)
                    }
                }
                repository.updatePlaylist(
                    playlist.copy(
                        videoIds = updatedVideoIds,
                        updatedAt = System.currentTimeMillis()
                    )
                )
                Result.success(Unit)
            } else {
                Result.failure(Exception("Playlist not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

class RemoveVideoFromPlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    suspend operator fun invoke(playlistId: String, videoId: String): Result<Unit> {
        return try {
            val playlist = repository.getPlaylistById(playlistId)
            if (playlist != null) {
                val updatedVideoIds = playlist.videoIds.toMutableList().apply {
                    remove(videoId)
                }
                repository.updatePlaylist(
                    playlist.copy(
                        videoIds = updatedVideoIds,
                        updatedAt = System.currentTimeMillis()
                    )
                )
                Result.success(Unit)
            } else {
                Result.failure(Exception("Playlist not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

class ReorderPlaylistVideosUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    suspend operator fun invoke(playlistId: String, newOrder: List<String>): Result<Unit> {
        return try {
            val playlist = repository.getPlaylistById(playlistId)
            if (playlist != null) {
                repository.updatePlaylist(
                    playlist.copy(
                        videoIds = newOrder,
                        updatedAt = System.currentTimeMillis()
                    )
                )
                Result.success(Unit)
            } else {
                Result.failure(Exception("Playlist not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

class GetSmartPlaylistVideosUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    suspend operator fun invoke(type: SmartPlaylistType): List<String> {
        return repository.getSmartPlaylistVideos(type)
    }
}

class ExportPlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    suspend operator fun invoke(playlistId: String, format: String = "m3u"): Result<String> {
        return try {
            val content = repository.exportPlaylist(playlistId, format)
            Result.success(content)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

class ImportPlaylistUseCase @Inject constructor(
    private val repository: PlaylistRepository
) {
    suspend operator fun invoke(content: String, format: String = "m3u"): Result<Playlist> {
        return try {
            val playlist = repository.importPlaylist(content, format)
            Result.success(playlist)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
