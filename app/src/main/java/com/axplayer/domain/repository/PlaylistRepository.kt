package com.axplayer.domain.repository

import com.axplayer.domain.entity.Playlist
import com.axplayer.domain.entity.SmartPlaylistType
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {
    fun getPlaylists(): Flow<List<Playlist>>
    suspend fun getPlaylistById(id: String): Playlist?
    suspend fun insertPlaylist(playlist: Playlist)
    suspend fun updatePlaylist(playlist: Playlist)
    suspend fun deletePlaylist(id: String)
    suspend fun getSmartPlaylistVideos(type: SmartPlaylistType): List<String>
    suspend fun exportPlaylist(playlistId: String, format: String): String
    suspend fun importPlaylist(content: String, format: String): Playlist
}
