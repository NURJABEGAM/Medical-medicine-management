package com.axplayer.presentation.player

import com.axplayer.domain.entity.PlaybackState
import com.axplayer.domain.entity.Video

data class PlayerState(
    val video: Video? = null,
    val playbackState: PlaybackState = PlaybackState(),
    val isLoading: Boolean = false,
    val error: String? = null
)
