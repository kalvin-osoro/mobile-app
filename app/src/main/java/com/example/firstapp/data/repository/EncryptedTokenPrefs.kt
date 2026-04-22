package com.example.firstapp.data.repository

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.firstapp.data.model.response.TokenResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import androidx.core.content.edit

class SecureTokenStorage @Inject constructor(
    @ApplicationContext context: Context
) {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs = EncryptedSharedPreferences.create(
        context,
        "spotify_secure_session",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveTokens(access: String, refresh: String) {
        prefs.edit() {
            putString("access_token", access)
                .putString("refresh_token", refresh)
        }
    }

    fun accessToken(): String?  = prefs.getString("access_token", null)
    fun refreshToken(): String? = prefs.getString("refresh_token", null)
//    fun isTokenExpired(): Boolean  =
//        System.currentTimeMillis() > prefs.getLong("expires_at", 0L

}