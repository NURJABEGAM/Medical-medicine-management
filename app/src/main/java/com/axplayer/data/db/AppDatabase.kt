package com.axplayer.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        VideoEntity::class,
        PlaylistEntity::class,
        SubtitleEntity::class,
        BookmarkEntity::class
    ],
    version = 2,
    exportSchema = true
)
@TypeConverters(PlaylistConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao
    abstract fun playlistDao(): PlaylistDao
    abstract fun subtitleDao(): SubtitleDao
    abstract fun bookmarkDao(): BookmarkDao

    companion object {
        const val DATABASE_NAME = "axplayer_database"
    }
}
