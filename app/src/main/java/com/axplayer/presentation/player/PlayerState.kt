package com.axplayer.presentation.player

import com.axplayer.domain.entity.*

data class PlayerState(
    val video: Video? = null,
    val playbackState: PlaybackState = PlaybackState(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val playbackSpeed: PlaybackSpeed = PlaybackSpeed.NORMAL,
    val loopMode: LoopMode = LoopMode.NONE,
    val aspectRatio: AspectRatio = AspectRatio.FIT,
    val videoFilter: VideoFilter = VideoFilter.DEFAULT,
    val equalizerSettings: EqualizerSettings = EqualizerSettings(),
    val subtitles: List<Subtitle> = emptyList(),
    val selectedSubtitle: Subtitle? = null,
    val subtitleSettings: SubtitleSettings = SubtitleSettings(),
    val audioTracks: List<AudioTrack> = emptyList(),
    val selectedAudioTrack: AudioTrack? = null,
    val bookmarks: List<VideoBookmark> = emptyList(),
    val sleepTimer: SleepTimer = SleepTimer.OFF,
    val showControls: Boolean = true,
    val isFullscreen: Boolean = false,
    val isPictureInPicture: Boolean = false,
    val queue: List<Video> = emptyList(),
    val currentQueueIndex: Int = -1,
    val showSubtitlePanel: Boolean = false,
    val showBookmarksPanel: Boolean = false,
    val showAudioTracksPanel: Boolean = false,
    val showVideoInfoPanel: Boolean = false,
    val showSpeedPanel: Boolean = false,
    val showAspectRatioPanel: Boolean = false,
    val showFiltersPanel: Boolean = false
)
