package com.example.firstapp.data.network

import com.example.firstapp.data.repository.TokenRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenRefreshInterceptor @Inject constructor(
    private val tokenRepository: TokenRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        //proactively refresh if token is expired before even making the call
        if (tokenRepository.isTokenExpired()) {
            runBlocking { tokenRepository.refreshAccessToken() }
        }
        return chain.proceed(chain.request())
    }
}