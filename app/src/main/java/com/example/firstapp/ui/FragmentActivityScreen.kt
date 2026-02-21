package com.example.firstapp.ui

import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import com.example.firstapp.R

@Composable
fun FragmentActivityScreen(
    onHomeClick: () -> Unit,
    onMessagesClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedItem == 0,
                    onClick = {
                        selectedItem = 0
                        onHomeClick()
                    },
                    icon = { Icon(painterResource(id = R.drawable.ic_home), contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = selectedItem == 1,
                    onClick = {
                        selectedItem = 1
                        onMessagesClick()
                    },
                    icon = {
                        BadgedBox(badge = { Badge { Text("7") } }) {
                            Icon(painterResource(id = R.drawable.ic_messages), contentDescription = "Messages")
                        }
                    },
                    label = { Text("Messages") }
                )
                NavigationBarItem(
                    selected = selectedItem == 2,
                    onClick = {
                        selectedItem = 2
                        onProfileClick()
                    },
                    icon = { Icon(painterResource(id = R.drawable.ic_profile), contentDescription = "Profile") },
                    label = { Text("Profile") }
                )
            }
        }
    ) { paddingValues ->
        AndroidView(
            factory = { context ->
                FrameLayout(context).apply {
                    id = R.id.flFragment
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        )
    }
}
