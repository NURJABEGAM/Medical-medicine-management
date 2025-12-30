package com.axplayer.presentation.library

import com.axplayer.domain.entity.LibraryFilter
import com.axplayer.domain.entity.Playlist
import com.axplayer.domain.entity.SortOrder
import com.axplayer.domain.entity.Video

data class LibraryState(
    val videos: List<Video> = emptyList(),
    val filteredVideos: List<Video> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val libraryFilter: LibraryFilter = LibraryFilter(),
    val hasPermission: Boolean = false,
    val selectedVideos: Set<String> = emptySet(),
    val isSelectionMode: Boolean = false,
    val playlists: List<Playlist> = emptyList(),
    val folders: List<String> = emptyList(),
    val selectedFolder: String? = null,
    val viewMode: ViewMode = ViewMode.GRID,
    val showFilterPanel: Boolean = false,
    val showSortPanel: Boolean = false,
    val showPlaylistDialog: Boolean = false
)

enum class ViewMode {
    GRID, LIST
}
