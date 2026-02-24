package com.example.firstapp.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstapp.Person

@Composable
fun MainScreen(
    onApplyClick: (Person) -> Unit,
    onRecyclerViewClick: () -> Unit,
    onFragmentClick: () -> Unit,
    onLibraryClick: () -> Unit,
    onRequestPermissions: () -> Unit,
    onShowDialog1: () -> Unit,
    onShowDialog2: () -> Unit,
    onShowDialog3: () -> Unit,
    onShowCustomToast: () -> Unit
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }

    var selectedMeat by remember { mutableStateOf("Chicken") }
    var cheese by remember { mutableStateOf(false) }
    var onions by remember { mutableStateOf(false) }
    var salad by remember { mutableStateOf(false) }
    var orderText by remember { mutableStateOf("") }

    var selectedMonth by remember { mutableStateOf("first") }
    val months = listOf("first", "second", "third", "fourth")
    var spinnerExpanded by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name") },
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name") },
                modifier = Modifier.weight(1f)
            )
        }
        OutlinedTextField(
            value = birthDate,
            onValueChange = { birthDate = it },
            label = { Text("Birth Date") },
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = country,
                onValueChange = { country = it },
                label = { Text("Country") },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                onApplyClick(Person(firstName, lastName, birthDate, country))
            }) {
                Text("APPLY")
            }
        }

        Text("What do you want on your burger?", fontSize = 20.sp, modifier = Modifier.align(Alignment.Start))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            listOf("Chicken", "Beef", "Pork").forEach { meat ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = selectedMeat == meat,
                        onClick = { selectedMeat = meat }
                    )
                    Text(meat)
                }
            }
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = cheese, onCheckedChange = { cheese = it })
                Text("Cheese")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = onions, onCheckedChange = { onions = it })
                Text("Onions")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = salad, onCheckedChange = { salad = it })
                Text("Salad")
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                orderText = "You ordered a burger with:\n$selectedMeat" +
                        (if (cheese) "\nCheese" else "") +
                        (if (onions) "\nOnions" else "") +
                        (if (salad) "\nSalad" else "")
            }) {
                Text("ORDER")
            }
            Button(onClick = onRecyclerViewClick) {
                Text("RECYCLER VIEW")
            }
        }

        Text(orderText, fontSize = 18.sp)

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { Toast.makeText(context, "Hi, I'm a toast!", Toast.LENGTH_LONG).show() }) {
                Text("SHOW TOAST")
            }
            Button(onClick = onShowCustomToast) {
                Text("CUSTOM TOAST")
            }
            Button(onClick = onRequestPermissions) {
                Text("START ACTIVITY")
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = onShowDialog1) { Text("DIALOG 1") }
            Button(onClick = onShowDialog2) { Text("DIALOG 2") }
            Button(onClick = onShowDialog3) { Text("DIALOG 3") }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = onFragmentClick) { Text("GO TO FRAGMENT") }

            Box {
                Button(onClick = { spinnerExpanded = true }) {
                    Text(selectedMonth)
                }
                DropdownMenu(expanded = spinnerExpanded, onDismissRequest = { spinnerExpanded = false }) {
                    months.forEach { month ->
                        DropdownMenuItem(
                            text = { Text(month) },
                            onClick = {
                                selectedMonth = month
                                spinnerExpanded = false
                                Toast.makeText(context, "You selected $month", Toast.LENGTH_LONG).show()
                            }
                        )
                    }
                }
            }
        }

        Button(onClick = onLibraryClick) {
            Text("GO TO LIBRARY")
        }
    }
}
