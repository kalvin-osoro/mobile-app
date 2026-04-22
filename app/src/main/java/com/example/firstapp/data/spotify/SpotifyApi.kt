package com.example.firstapp.data.spotify

//import com.example.firstapp.data.model.response.SpotifyProfileResponse
import com.example.firstapp.data.model.response.SpotifyTracksResponse
import retrofit2.http.GET

interface SpotifyApi {

//    @GET("v1/me")
//    suspend fun getProfile(): SpotifyProfileResponse

//    https://api.spotify.com/v1/me/tracks
    @GET("v1/me/tracks")
    suspend fun getSavedTracks(): SpotifyTracksResponse
}