package com.example.firstapp.data

import android.app.Activity
import android.content.Intent
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse

class SpotifyAuthManager(
    private val activity: Activity
) {
    fun openLogin(requestCode: Int, clientId: String, redirectUri: String) {
        val request = AuthorizationRequest.Builder(
            clientId,
            AuthorizationResponse.Type.CODE,
            redirectUri

        )
            .setScopes(arrayOf("user-read-email"))
            .build()

        AuthorizationClient.openLoginActivity(activity, requestCode, request)
    }

    fun handleResult(
        requestCode: Int,
        resultCode: Int,
        intent: Intent?
    ): String? {
        val response = AuthorizationClient.getResponse(resultCode, intent)

        return if (response.type == AuthorizationResponse.Type.CODE) {
            response.code
        } else {
            null
        }
    }
}