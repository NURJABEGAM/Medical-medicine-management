package com.axplayer.presentation.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axplayer.domain.repository.VideoRepository
import com.axplayer.domain.usecase.GetVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val getVideosUseCase: GetVideosUseCase,
    private val videoRepository: VideoRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LibraryState())
    val state: StateFlow<LibraryState> = _state.asStateFlow()

    fun loadVideos() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            
            when (_state.value.filterType) {
                FilterType.ALL -> getVideosUseCase()
                FilterType.RECENT -> getVideosUseCase.getRecent()
                FilterType.FAVORITES -> getVideosUseCase.getFavorites()
            }
                .catch { exception ->
                    Timber.e(exception, "Error loading videos")
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = exception.message ?: "Unknown error"
                        )
                    }
                }
                .collect { videos ->
                    val sortedVideos = sortVideos(videos, _state.value.sortType)
                    _state.update {
                        it.copy(
                            videos = sortedVideos,
                            isLoading = false,
                            error = null
                        )
                    }
                }
        }
    }

    fun refreshVideos() {
        viewModelScope.launch {
            try {
                videoRepository.refreshVideos()
                loadVideos()
            } catch (e: Exception) {
                Timber.e(e, "Error refreshing videos")
                _state.update {
                    it.copy(error = e.message ?: "Failed to refresh videos")
                }
            }
        }
    }

    fun searchVideos(query: String) {
        _state.update { it.copy(searchQuery = query) }
        
        if (query.isBlank()) {
            loadVideos()
            return
        }

        viewModelScope.launch {
            getVideosUseCase.searchVideos(query)
                .catch { exception ->
                    Timber.e(exception, "Error searching videos")
                    _state.update {
                        it.copy(error = exception.message ?: "Search failed")
                    }
                }
                .collect { videos ->
                    val sortedVideos = sortVideos(videos, _state.value.sortType)
                    _state.update { it.copy(videos = sortedVideos) }
                }
        }
    }

    fun setFilter(filterType: FilterType) {
        _state.update { it.copy(filterType = filterType) }
        loadVideos()
    }

    fun setSort(sortType: SortType) {
        _state.update { it.copy(sortType = sortType) }
        val sortedVideos = sortVideos(_state.value.videos, sortType)
        _state.update { it.copy(videos = sortedVideos) }
    }

    fun setPermissionGranted(granted: Boolean) {
        _state.update { it.copy(hasPermission = granted) }
        if (granted) {
            refreshVideos()
        }
    }

    fun toggleFavorite(videoId: String) {
        viewModelScope.launch {
            try {
                videoRepository.toggleFavorite(videoId)
            } catch (e: Exception) {
                Timber.e(e, "Error toggling favorite")
            }
        }
    }

    private fun sortVideos(videos: List<com.axplayer.domain.entity.Video>, sortType: SortType) =
        when (sortType) {
            SortType.NAME -> videos.sortedBy { it.title.lowercase() }
            SortType.DATE_MODIFIED -> videos.sortedByDescending { it.dateModified }
            SortType.DURATION -> videos.sortedByDescending { it.duration }
            SortType.SIZE -> videos.sortedByDescending { it.size }
        }
}
