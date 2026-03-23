package com.example.firstapp.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.data.repository.TokenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpotifyViewModel @Inject constructor(
    private val tokenRepoitory: TokenRepository
) : ViewModel() {
    private val _authCode = MutableStateFlow<String?>(null)
    val authCode: StateFlow<String?> = _authCode

    private val  _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState


    fun onAuthCodeReceived(code: String) {
        _authCode.value = code
        exchangeToken(code) // immediately kick off exchange
    }

    private fun exchangeToken(code: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            tokenRepoitory.exchangeCodeForTokens(code)
                .onSuccess { _uiState.value = AuthUiState.Success }
                .onFailure { _uiState.value =AuthUiState.Error(it.message) }
        }
    }
}

sealed class AuthUiState {
    object Idle         : AuthUiState()
    object Loading      :AuthUiState()
    object Success      : AuthUiState()
    data class Error (val message: String?) : AuthUiState()
}


