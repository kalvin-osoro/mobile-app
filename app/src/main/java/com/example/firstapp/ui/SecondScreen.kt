package com.example.firstapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstapp.Person

@Composable
fun SecondScreen(person: Person, onBackClick: () -> Unit, onLaunchThirdClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = person.toString(), fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onLaunchThirdClick) {
            Text("GO TO 3RD")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onBackClick) {
            Text("GO BACK")
        }
    }
}
