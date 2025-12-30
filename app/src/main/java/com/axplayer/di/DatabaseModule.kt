package com.axplayer.di

import android.content.Context
import androidx.room.Room
import com.axplayer.data.db.AppDatabase
import com.axplayer.data.db.VideoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideVideoDao(database: AppDatabase): VideoDao {
        return database.videoDao()
    }

    @Provides
    @Singleton
    fun providePlaylistDao(database: AppDatabase) = database.playlistDao()

    @Provides
    @Singleton
    fun provideSubtitleDao(database: AppDatabase) = database.subtitleDao()

    @Provides
    @Singleton
    fun provideBookmarkDao(database: AppDatabase) = database.bookmarkDao()
}
