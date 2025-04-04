/*
The TodoScreen composable collects the list of to-dos from the ViewModel.

It uses a LazyColumn to efficiently display each item.

TodoItem is another composable that displays the properties of a single to-do.
 */

package com.example.vod.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.vod.domain.model.Todo

@Composable
fun TodoScreen(viewModel: TodoViewModel) {
    // Observing the state from the ViewModel
    val todoList by viewModel.todoList.collectAsState()

    Column {
        Text(text = "Todo List from JSONPlaceholder")
        LazyColumn {
            // Display each Todo item using a composable
            items(todoList) { todo ->
                TodoItem(todo)
            }
        }
    }
}

@Composable
fun TodoItem(todo: Todo) {
    // Displaying the details of a Todo
    Column {
        Text(text = "User ID: ${todo.userId}")
        Text(text = "Todo ID: ${todo.id}")
        Text(text = "Title: ${todo.title}")
        Text(text = "Completed: ${todo.completed}")
        Text(text = "----------------------------")
    }
}
