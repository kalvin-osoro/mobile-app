package com.example.firstapp.presentation.dashboard

import androidx.lifecycle.ViewModel
import com.example.firstapp.domain.repository.SpotifyLoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SpotifyViewModel(
    private val loginUseCase: SpotifyLoginUseCase
) : ViewModel() {
    private val _authCode = MutableStateFlow<String?>(null)
    val authCode: StateFlow<String?> = _authCode

    fun onAuthCodeReceived(code: String) {
        _authCode.value = code
    }
}