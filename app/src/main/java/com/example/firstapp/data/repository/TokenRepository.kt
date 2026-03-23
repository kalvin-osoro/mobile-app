package com.example.firstapp.data.repository

import com.example.firstapp.data.model.TokenResponse
import com.example.firstapp.data.spotify.SpotifyAuthManager
import com.example.firstapp.data.spotify.SpotifyTokenService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenRepository @Inject constructor(
    private val tokenService: SpotifyTokenService,
    private val authManager: SpotifyAuthManager,
    private val prefs: EncryptedTokenPrefs
)  {

    suspend fun exchangeCodeForTokens(code: String): Result<TokenResponse> {
        val verifier = authManager.getStoredVerifier()
            ?: return Result.failure(Exception("No PKCE verifier found"))

        return runCatching {
            tokenService.exchangeToken(
                grantType   = "authorization_code",
                code        = code,
                redirectUri = REDIRECT_URI,
                clientId    = CLIENT_ID,
                codeVerifier = verifier  // sent instead of client secret
            ).also { prefs.saveTokens(it) }
        }
    }

    suspend fun refreshAccessToken(): Result<TokenResponse> {
        val refreshToken = prefs.getRefreshToken()
            ?: return Result.failure(Exception("No refresh token stored"))

        return runCatching {
            tokenService.refreshToken(
                grantType    = "refresh_token",
                refreshToken = refreshToken,
                clientId     = CLIENT_ID
            ).also { prefs.saveTokens(it) }
        }
    }

    fun getAccessToken(): String? = prefs.getAccessToken()
    fun isTokenExpired(): Boolean = prefs.isTokenExpired()
}