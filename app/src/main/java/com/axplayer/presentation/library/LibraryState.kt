package com.axplayer.presentation.library

import com.axplayer.domain.entity.Video

data class LibraryState(
    val videos: List<Video> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val filterType: FilterType = FilterType.ALL,
    val sortType: SortType = SortType.DATE_MODIFIED,
    val hasPermission: Boolean = false
)

enum class FilterType {
    ALL, RECENT, FAVORITES
}

enum class SortType {
    NAME, DATE_MODIFIED, DURATION, SIZE
}
