package com.axplayer.di

import com.axplayer.data.repository.BookmarkRepositoryImpl
import com.axplayer.data.repository.PlaylistRepositoryImpl
import com.axplayer.data.repository.SubtitleRepositoryImpl
import com.axplayer.data.repository.VideoRepositoryImpl
import com.axplayer.domain.repository.BookmarkRepository
import com.axplayer.domain.repository.PlaylistRepository
import com.axplayer.domain.repository.SubtitleRepository
import com.axplayer.domain.repository.VideoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindVideoRepository(
        videoRepositoryImpl: VideoRepositoryImpl
    ): VideoRepository

    @Binds
    @Singleton
    abstract fun bindPlaylistRepository(
        playlistRepositoryImpl: PlaylistRepositoryImpl
    ): PlaylistRepository

    @Binds
    @Singleton
    abstract fun bindSubtitleRepository(
        subtitleRepositoryImpl: SubtitleRepositoryImpl
    ): SubtitleRepository

    @Binds
    @Singleton
    abstract fun bindBookmarkRepository(
        bookmarkRepositoryImpl: BookmarkRepositoryImpl
    ): BookmarkRepository
}
