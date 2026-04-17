package com.example.firstapp.presentation.dashboard.screens.home

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.firstapp.presentation.dashboard.SpotifyViewModel
import com.example.firstapp.data.spotify.SpotifyAuthManager


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpotifyLoginScreen(
    viewModel: SpotifyViewModel,
    spotifyAuthManager: SpotifyAuthManager) {

//    val activity = LocalContext.current as Activity
    val context = LocalContext.current
    val activity = context as Activity

    val authCode by viewModel.authCode.collectAsState()

    val spotifyLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->

            Log.d("SpotifyAuth", "Result received")

            val code = spotifyAuthManager.getAuthCode(
                result.resultCode,
                result.data
            )

            code?.let {
                viewModel.onAuthCodeReceived(it)
            }
        }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Spotify Login") }
            )
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    Log.d("Spotify", "Login button clicked")
                    val intent =
                        spotifyAuthManager.createLoginIntent(activity)

                    spotifyLauncher.launch(intent)
                }
            ) {
                Text("Login with Spotify")
            }

        }

        authCode?.let {
            Text("Auth Code: $it")
        }
    }

    }
