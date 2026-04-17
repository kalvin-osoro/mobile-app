package com.example.firstapp.data.spotify

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Base64
import android.util.Log
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import java.security.MessageDigest
import java.security.SecureRandom
import androidx.core.content.edit

class SpotifyAuthManager(
    private val clientId: String,
    private val redirectUri: String,
    private val context: Context
) {

    // ── PKCE helpers ────────────────────────────────────────────────────────

    private fun generateCodeVerifier(): String {
        val bytes = ByteArray(64)
        SecureRandom().nextBytes(bytes)
        return Base64.encodeToString(
            bytes,
            Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING
        )
    }

    private fun generateCodeChallenge(verifier: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
            .digest(verifier.toByteArray(Charsets.US_ASCII))
        return Base64.encodeToString(
            digest,
            Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING
        )
    }

    // persist the verifier so it survives the activity re-creation that
   //  happens when spotify returns the auth code via deep-link / redirect
    private fun  saveVerifier(verifier: String) {
        context.getSharedPreferences("spotify_pkce", Context.MODE_PRIVATE)
            .edit() { putString("code_verifier", verifier) }
    }

    fun getStoredVerifier(): String? =
        context.getSharedPreferences("spotify_pkce", Context.MODE_PRIVATE)
            .getString("code_verifier", null)


    // ── Public API ───────────────────────────────────────────────────────────




    fun createLoginIntent(activity: Activity): Intent {


        val verifier = generateCodeVerifier()
        val challenge = generateCodeChallenge(verifier)
        saveVerifier(verifier) // store before launching spotify

        val request = AuthorizationRequest.Builder(
            clientId,
            AuthorizationResponse.Type.CODE,
            redirectUri
        )
            .setScopes(arrayOf(
                "user-read-email",
                "user-read-playback-state",
                "user-modify-playback-state",
                "user-library-read",
                "playlist-read-private",
                "playlist-modify-public",
                "playlist-modify-private",
                "user-top-read"
                ))
            .setCustomParam("code_challenge_method", "s256")
            .setCustomParam("code_challenge", challenge)
            .build()

        return AuthorizationClient.createLoginActivityIntent(activity, request)
    }

    fun getAuthCode(resultCode: Int, intent: Intent?): String? {

        val response = AuthorizationClient.getResponse(resultCode, intent)

        Log.d("SpotifyAuth", "Response type: ${response.type}")

        when (response.type) {
            AuthorizationResponse.Type.CODE -> {
                Log.d("SpotifyAuth", "Auth code received: ${response.code}")
                return response.code
            }
            AuthorizationResponse.Type.ERROR -> {
                Log.e("SpotifyAuth", "Auth error: ${response.error}")
            }
            AuthorizationResponse.Type.TOKEN -> {
                Log.d("SpotifyAuth", "Access token received")
            }
            else -> {
                Log.d("SpotifyAuth", "Unknown response")
            }
        }
        return null

//        return if (response.type == AuthorizationResponse.Type.CODE) {
//            response.code
//        } else null
    }
}