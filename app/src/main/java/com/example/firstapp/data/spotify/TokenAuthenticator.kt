package com.example.firstapp.data.spotify

import com.example.firstapp.data.repository.SecureTokenStorage
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenAuthenticator @Inject constructor(
//    private val tokenRepository: TokenRepository
    private val tokenStorage: SecureTokenStorage,
    private val authApi: SpotifyAuthApi
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {

        val refreshToken = tokenStorage.refreshToken() ?: return null

        val newToken = runBlocking {
            authApi.refreshToken(refreshToken = refreshToken)
        }


        tokenStorage.saveTokens(
            access = newToken.accessToken,
            refresh = refreshToken
        )

        return response.request.newBuilder()
            .header("Authorization", "Bearer ${newToken.accessToken}")
            .build()
    }
}