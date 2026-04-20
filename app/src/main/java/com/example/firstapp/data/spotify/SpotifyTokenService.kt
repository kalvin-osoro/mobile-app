package com.example.firstapp.data.spotify

import com.example.firstapp.data.model.response.TokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SpotifyTokenService {

    @FormUrlEncoded
    @POST("api/token")
    suspend fun exchangeToken(
        @Field("grant_type")    grantType: String,
        @Field("code")          code: String,
        @Field("redirect_uri")  redirectUri: String,
        @Field("client_id")     clientId: String,
        @Field("code_verifier") codeVerifier: String  // ← PKCE, replaces client_secret
    ): TokenResponse

    // Refresh token -> new access token
    @FormUrlEncoded
    @POST("api/token")
    suspend fun refreshToken(
    @Field("grant_type")    grantType: String,
    @Field("refresh_token") refreshToken: String,
    @Field("client_id")     clientId: String
    ): TokenResponse
}