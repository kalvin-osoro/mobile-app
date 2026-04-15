# mobile-app


The cleanest structure is:

Activity → receives result
Activity → passes code to ViewModel
ViewModel → sends to backend


# Next-Level Upgrade (Optional)

If you want it even cleaner, we can:

Use Activity Result API instead of onActivityResult

Wrap login result in a callbackFlow

Inject with Hilt properly

Convert everything to fully suspend-based flow

#  Final Architecture

SpotifyLoginScreen (Composable)
↓
MainActivity
↓
SpotifyAuthManager
↓
Spotify SDK
↓
Result → Activity
↓
ViewModel
↓
UI updates

# We will use:

Activity Result API

Compose launcher

Clean architecture separation

We’ll structure it like this:


presentation/
SpotifyViewModel
screens/
SpotifyLoginScreen

data/
spotify/
SpotifyAuthManager

domain/
usecase/
SpotifyLoginUseCase


# fina implementation

data/
spotify/
SpotifyAuthManager
SpotifyRepositoryImpl
SpotifySessionManager

domain/
repository/
SpotifyRepository
usecase/
SpotifyLoginUseCase

presentation/
dashboard/
SpotifyViewModel
SpotifyUiState
screens/
SpotifyLoginScreen

di/
SpotifyModule


# Final Flow
User taps Login
↓
Compose Button
↓
SpotifyAuthManager creates intent
↓
Spotify Login Activity
↓
User authorizes
↓
Redirect back to app
↓
ActivityResultLauncher receives result
↓
Extract auth code
↓
Send to ViewModel
↓
UI updates