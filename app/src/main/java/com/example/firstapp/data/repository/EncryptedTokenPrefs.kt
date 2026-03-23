package com.example.firstapp.data.repository

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import javax.inject.Inject

class EncryptedTokenPrefs @Inject constructor(context: Context) {

    private val prefs = EncryptedSharedPreferences.create(
        context,
        "spotify_tokens",
        MasterKey.Builder(context).setKeyScheme(MasterKey.keyScheme.AES256_GCM).build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveTokens(response: TokenResponse) {
        prefs.edit()
            .putString("access_token", response.accessToken)
            .putString("refresh_token", response.refreshToken)
            .putLong("expires_at",
                System.currentTimeMillis() + response.expiresIn * 1000L)
            .apply()
    }

    fun getAccessToken(): String?  = prefs.getString("access_token", null)
    fun getRefreshToken(): String? = prefs.getString("refresh_token", null)
    fun isTokenExpired(): Boolean  =
        System.currentTimeMillis() > prefs.getLong("expires_at", 0L

}