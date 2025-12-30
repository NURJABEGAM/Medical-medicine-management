package com.axplayer.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.axplayer.domain.entity.Playlist
import com.axplayer.domain.entity.SmartPlaylistType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "playlists")
data class PlaylistEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val videoIds: String,
    val createdAt: Long,
    val updatedAt: Long,
    val isSmartPlaylist: Boolean,
    val smartPlaylistType: String?,
    val thumbnailUri: String?,
    val isFavorite: Boolean
) {
    fun toDomain(): Playlist {
        return Playlist(
            id = id,
            name = name,
            description = description,
            videoIds = PlaylistConverters.fromStringToList(videoIds),
            createdAt = createdAt,
            updatedAt = updatedAt,
            isSmartPlaylist = isSmartPlaylist,
            smartPlaylistType = smartPlaylistType?.let { SmartPlaylistType.valueOf(it) },
            thumbnailUri = thumbnailUri,
            isFavorite = isFavorite
        )
    }

    companion object {
        fun fromDomain(playlist: Playlist): PlaylistEntity {
            return PlaylistEntity(
                id = playlist.id,
                name = playlist.name,
                description = playlist.description,
                videoIds = PlaylistConverters.fromListToString(playlist.videoIds),
                createdAt = playlist.createdAt,
                updatedAt = playlist.updatedAt,
                isSmartPlaylist = playlist.isSmartPlaylist,
                smartPlaylistType = playlist.smartPlaylistType?.name,
                thumbnailUri = playlist.thumbnailUri,
                isFavorite = playlist.isFavorite
            )
        }
    }
}

class PlaylistConverters {
    companion object {
        private val gson = Gson()
        
        @TypeConverter
        fun fromListToString(list: List<String>): String {
            return gson.toJson(list)
        }
        
        @TypeConverter
        fun fromStringToList(value: String): List<String> {
            val listType = object : TypeToken<List<String>>() {}.type
            return gson.fromJson(value, listType)
        }
    }
}
