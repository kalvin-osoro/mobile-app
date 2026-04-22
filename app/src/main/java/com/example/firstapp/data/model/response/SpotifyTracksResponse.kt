package com.example.firstapp.data.model.response

import com.google.gson.annotations.SerializedName

data class SpotifyTracksResponse(
    @SerializedName("access_token") val accessToken: String,
)
