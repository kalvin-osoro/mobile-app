package com.example.firstapp.data

import com.example.firstapp.data.spotify.SpotifyAuthManager
import com.example.firstapp.domain.repository.SpotifyRepositoryInterface
import javax.inject.Inject

class SpotifyRepositoryImpl @Inject constructor(
    private val authManager: SpotifyAuthManager
) : SpotifyRepositoryInterface {
    override suspend fun login(): Result<String> {
        return try {
            // This will actually be triggered from Activity
            Result.success("Login started")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}