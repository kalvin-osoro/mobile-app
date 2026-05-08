package com.example.firstapp.data.repository

import com.example.firstapp.data.spotify.SpotifyAuthManager
import com.example.firstapp.domain.repository.SpotifyRepositoryInterface
import javax.inject.Inject

class bSpotifyRepositoryImpl @Inject constructor(
    private val authManager: SpotifyAuthManager
) : SpotifyRepositoryInterface {
    override suspend fun login(): Result<String> {
        return try {
            Result.success("Login started")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}