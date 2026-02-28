package com.example.firstapp.data

import com.example.firstapp.domain.repository.SpotifyRepositoryInterface

class SpotifyRepositoryImpl(
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