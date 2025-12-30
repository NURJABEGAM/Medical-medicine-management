package com.axplayer.data.datasource

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.axplayer.data.db.VideoEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalVideoDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun loadVideosFromDevice(): List<VideoEntity> = withContext(Dispatchers.IO) {
        val videos = mutableListOf<VideoEntity>()
        val contentResolver: ContentResolver = context.contentResolver

        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.TITLE,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.MIME_TYPE,
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media.DATE_MODIFIED,
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.WIDTH,
            MediaStore.Video.Media.HEIGHT,
            MediaStore.Video.Media.BITRATE
        )

        val sortOrder = "${MediaStore.Video.Media.DATE_MODIFIED} DESC"

        try {
            contentResolver.query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                sortOrder
            )?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
                val displayNameColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
                val titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE)
                val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
                val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)
                val mimeTypeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.MIME_TYPE)
                val dateAddedColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_ADDED)
                val dateModifiedColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_MODIFIED)
                val dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
                val widthColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH)
                val heightColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT)
                val bitrateColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BITRATE)

                while (cursor.moveToNext()) {
                    try {
                        val id = cursor.getLong(idColumn)
                        val displayName = cursor.getString(displayNameColumn) ?: "Unknown"
                        val title = cursor.getString(titleColumn) ?: displayName
                        val duration = cursor.getLong(durationColumn)
                        val size = cursor.getLong(sizeColumn)
                        val mimeType = cursor.getString(mimeTypeColumn) ?: "video/*"
                        val dateAdded = cursor.getLong(dateAddedColumn) * 1000 // Convert to milliseconds
                        val dateModified = cursor.getLong(dateModifiedColumn) * 1000
                        val path = cursor.getString(dataColumn) ?: ""
                        val width = cursor.getInt(widthColumn)
                        val height = cursor.getInt(heightColumn)
                        val bitrate = cursor.getInt(bitrateColumn)

                        val contentUri = ContentUris.withAppendedId(
                            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                            id
                        )

                        val thumbnailUri = getThumbnailUri(id)

                        val video = VideoEntity(
                            id = id.toString(),
                            uri = contentUri.toString(),
                            title = title,
                            displayName = displayName,
                            duration = duration,
                            size = size,
                            mimeType = mimeType,
                            dateAdded = dateAdded,
                            dateModified = dateModified,
                            path = path,
                            thumbnailUri = thumbnailUri?.toString(),
                            width = width,
                            height = height,
                            bitrate = bitrate,
                            fps = 0f
                        )

                        videos.add(video)
                    } catch (e: Exception) {
                        Timber.e(e, "Error parsing video from cursor")
                    }
                }
            }
        } catch (e: Exception) {
            Timber.e(e, "Error loading videos from device")
        }

        Timber.d("Loaded ${videos.size} videos from device")
        return@withContext videos
    }

    private fun getThumbnailUri(videoId: Long): Uri? {
        return try {
            ContentUris.withAppendedId(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                videoId
            )
        } catch (e: Exception) {
            Timber.e(e, "Error getting thumbnail URI")
            null
        }
    }
}
