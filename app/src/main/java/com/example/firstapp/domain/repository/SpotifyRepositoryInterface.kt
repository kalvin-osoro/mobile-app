package com.example.firstapp.domain.repository

interface SpotifyRepositoryInterface {
    suspend fun login(): Result<String> //return auth code
}