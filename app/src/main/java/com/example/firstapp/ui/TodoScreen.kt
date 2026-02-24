package com.example.firstapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstapp.Todo

@Composable
fun TodoScreen(
    todoList: List<Todo>,
    onAddTodo: (String) -> Unit,
    onCheckedChange: (Todo, Boolean) -> Unit
) {
    var newTodoTitle by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(todoList) { todo ->
                TodoItemRow(todo = todo, onCheckedChange = { isChecked ->
                    onCheckedChange(todo, isChecked)
                })
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = newTodoTitle,
                onValueChange = { newTodoTitle = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("New todo") }
            )
            Button(onClick = {
                if (newTodoTitle.isNotBlank()) {
                    onAddTodo(newTodoTitle)
                    newTodoTitle = ""
                }
            }) {
                Text("Add")
            }
        }
    }
}

@Composable
fun TodoItemRow(todo: Todo, onCheckedChange: (Boolean) -> Unit) {
    var checked by remember { mutableStateOf(todo.isChecked) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = todo.title,
            fontSize = 24.sp,
            modifier = Modifier.weight(1f)
        )
        Checkbox(
            checked = checked,
            onCheckedChange = {
                checked = it
                onCheckedChange(it)
            }
        )
    }
}
