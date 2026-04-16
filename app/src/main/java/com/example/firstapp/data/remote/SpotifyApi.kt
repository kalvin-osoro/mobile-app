package com.example.firstapp.data.remote

import retrofit2.http.GET

interface SpotifyApi {

    @GET("v1/me")
    suspend fun getProfile(): SpotifyProfileResponse

    @GET("v1/me/tracks")
    suspend fun getSavedTracks(): SpotifyTracksResponse
}