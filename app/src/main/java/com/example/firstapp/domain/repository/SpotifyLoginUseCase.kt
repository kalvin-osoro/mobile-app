package com.example.firstapp.domain.repository

import android.R

class SpotifyLoginUseCase (
    private val repository: SpotifyRepositoryInterface
){
    suspend operator fun invoke(): Result<String> {
        return repository.login()
    }

}