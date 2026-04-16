package com.example.firstapp.di

import com.example.firstapp.data.SpotifyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SpotifyModule {

    @Binds
    @Singleton
    abstract fun bindSpotifyRepository(
        impl: SpotifyRepositoryImpl
    ): SpotifyRepository
}