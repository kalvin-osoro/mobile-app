package com.example.firstapp.presentation.dashboard.screens.home

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.firstapp.presentation.dashboard.SpotifyViewModel
import com.example.firstapp.data.spotify.SpotifyAuthManager

@Composable
fun SpotifyLoginScreen(viewModel: SpotifyViewModel,
                       spotifyAuthManager: SpotifyAuthManager) {


    val context = LocalContext.current
    val activity = context as Activity

    val authCode by viewModel.authCode.collectAsState()

    val spotifyLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val code = spotifyAuthManager.getAuthCode(
                result.resultCode,
                result.data
            )


            code?.let {
                viewModel.onAuthCodeReceived(it)
            }
        }

    Button(onClick = {
        val intent = spotifyAuthManager.createLoginIntent(activity)

        spotifyLauncher.launch(intent)

    }) {
        Text("Login with Spotify")
    }

    authCode?.let {
        Text("Auth Code: $it")
    }
}