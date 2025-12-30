package com.axplayer.domain.usecase

import com.axplayer.domain.entity.VideoBookmark
import com.axplayer.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarksForVideoUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    operator fun invoke(videoId: String): Flow<List<VideoBookmark>> {
        return repository.getBookmarksForVideo(videoId)
    }
}

class AddBookmarkUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    suspend operator fun invoke(
        videoId: String,
        position: Long,
        title: String,
        description: String = ""
    ): Result<VideoBookmark> {
        return try {
            val bookmark = VideoBookmark(
                id = System.currentTimeMillis().toString(),
                videoId = videoId,
                position = position,
                title = title,
                description = description
            )
            repository.insertBookmark(bookmark)
            Result.success(bookmark)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

class UpdateBookmarkUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    suspend operator fun invoke(bookmark: VideoBookmark): Result<Unit> {
        return try {
            repository.updateBookmark(bookmark)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

class DeleteBookmarkUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    suspend operator fun invoke(bookmarkId: String): Result<Unit> {
        return try {
            repository.deleteBookmark(bookmarkId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

class GetAllBookmarksUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    operator fun invoke(): Flow<List<VideoBookmark>> {
        return repository.getAllBookmarks()
    }
}
