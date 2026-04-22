package com.example.firstapp.di

import android.content.Context
import androidx.room.Room
import com.example.firstapp.data.local.SpotifyDatabase
import com.example.firstapp.data.local.TrackDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): SpotifyDatabase {

        return Room.databaseBuilder(
            context,
            SpotifyDatabase::class.java,
            "spotify_db"
        ).build()
    }

    @Provides
    fun provideTrackDao(db: SpotifyDatabase): TrackDao {
        return db.trackDao()
    }
}