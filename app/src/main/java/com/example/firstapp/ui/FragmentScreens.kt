package com.example.firstapp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstapp.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FirstFragmentScreen() {
    val images = listOf(
        R.drawable.img1,
        R.drawable.img2,
        R.drawable.img3,
        R.drawable.img4
    )
    val pagerState = rememberPagerState(pageCount = { images.size })
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            edgePadding = 0.dp
        ) {
            images.indices.forEach { index ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text("Tab ${index + 1}") }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                painter = painterResource(id = images[page]),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Composable
fun SecondFragmentScreen(onShowNotificationClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Second Fragment", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onShowNotificationClick) {
            Text("Show Notification")
        }
    }
}

@Composable
fun ThirdFragmentScreen(
    onSave: (String, Int, Boolean) -> Unit,
    onLoad: () -> Triple<String?, Int?, Boolean>
) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var isAdult by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text("Adult?", fontSize = 26.sp)
            Checkbox(checked = isAdult, onCheckedChange = { isAdult = it })
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = {
                val data = onLoad()
                name = data.first ?: ""
                age = data.second?.toString() ?: ""
                isAdult = data.third
            }, modifier = Modifier.weight(1f)) {
                Text("LOAD")
            }
            Button(onClick = {
                onSave(name, age.toIntOrNull() ?: 0, isAdult)
            }, modifier = Modifier.weight(1f)) {
                Text("SAVE")
            }
        }
    }
}
