package com.example.firstapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.firstapp.ui.TodoScreen
import com.example.firstapp.ui.theme.FirstAppTheme

class RecyclerviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FirstAppTheme {
                val todoList = remember {
                    mutableStateListOf(
                        Todo("Follow AndroidDevs", false),
                        Todo("Learn recycler view", false),
                        Todo("Eat", false),
                        Todo("Sleep", false),
                        Todo("Learn", false)
                    )
                }

                TodoScreen(
                    todoList = todoList,
                    onAddTodo = { title ->
                        todoList.add(Todo(title, false))
                    },
                    onCheckedChange = { todo, isChecked ->
                        val index = todoList.indexOf(todo)
                        if (index != -1) {
                            todoList[index] = todo.copy(isChecked = isChecked)
                        }
                    }
                )
            }
        }
    }
}
