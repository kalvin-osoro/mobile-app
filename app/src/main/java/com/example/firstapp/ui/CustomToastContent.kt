package com.example.firstapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstapp.R

@Composable
fun CustomToastContent() {
    Row(
        modifier = Modifier
            .background(Color(0xFF00E5FF)) // holo_blue_bright equivalent
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_notification),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = "This is a custom toast",
            fontSize = 26.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
