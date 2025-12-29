package com.axplayer.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [VideoEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao

    companion object {
        const val DATABASE_NAME = "axplayer_database"
    }
}
