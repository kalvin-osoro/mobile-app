package com.example.firstapp.data.network

import com.example.firstapp.data.repository.TokenRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenRepository: TokenRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // 1. Attach current access token to every request
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${tokenRepository.getAccessToken()}")
            .build()

        val response = chain.proceed(request)

        // 2. if 401 -> token expired, refresh and retry once

        if (response.code == 401) {
            response.close()

            val refreshed = runBlocking {
                tokenRepository.refreshAccessToken()
            }

            return if (refreshed.isSuccess) {
                //Retry the original request with new token
                val retryRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${tokenRepository.getAccessToken()}")
                    .build()
                chain.proceed(retryRequest)
            } else  {
                //Refresh failed - user needs to login again
                response
            }
        }
        return response
    }


}