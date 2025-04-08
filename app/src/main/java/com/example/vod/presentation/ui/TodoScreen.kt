package com.example.vod.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.vod.presentation.viewmodel.TodoViewModel

@Composable
fun TodoScreen(viewModel: TodoViewModel) {
    // Observe the Todo state from the ViewModel.
    val todo by viewModel.todo.collectAsState()

    Column {
        Text(text = "API response:")
        Text(text = todo.value)
    }
}
