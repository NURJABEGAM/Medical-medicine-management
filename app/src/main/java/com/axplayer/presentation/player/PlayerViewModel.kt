package com.axplayer.presentation.player

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axplayer.domain.entity.PlaybackState
import com.axplayer.domain.usecase.PlayVideoUseCase
import com.axplayer.domain.usecase.SavePlaybackStateUseCase
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
class PlayerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val playVideoUseCase: PlayVideoUseCase,
    private val savePlaybackStateUseCase: SavePlaybackStateUseCase
) : ViewModel() {

    private val videoId: String = checkNotNull(savedStateHandle["videoId"])

    private val _state = MutableStateFlow(PlayerState())
    val state: StateFlow<PlayerState> = _state.asStateFlow()

    init {
        loadVideo()
    }

    private fun loadVideo() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            
            playVideoUseCase(videoId)
                .catch { exception ->
                    Timber.e(exception, "Error loading video")
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = exception.message ?: "Failed to load video"
                        )
                    }
                }
                .collect { video ->
                    _state.update {
                        it.copy(
                            video = video,
                            isLoading = false,
                            error = null
                        )
                    }
                }
        }
    }

    fun updatePlaybackState(playbackState: PlaybackState) {
        _state.update { it.copy(playbackState = playbackState) }
    }

    fun savePlaybackPosition(position: Long) {
        viewModelScope.launch {
            try {
                savePlaybackStateUseCase(videoId, position)
            } catch (e: Exception) {
                Timber.e(e, "Error saving playback position")
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            try {
                playVideoUseCase.toggleFavorite(videoId)
            } catch (e: Exception) {
                Timber.e(e, "Error toggling favorite")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        val currentPosition = _state.value.playbackState.currentPosition
        savePlaybackPosition(currentPosition)
    }
}
