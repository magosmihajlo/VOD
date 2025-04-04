package com.example.vod.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.vod.data.repository.TodoRepositoryImpl

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the repository instance.
        // In a production app, you might use dependency injection instead.
        val repository = TodoRepositoryImpl()

        // Create the ViewModel and pass the repository to it.
        val viewModel = TodoViewModel(repository)

        setContent {
            // Set up the UI by passing the ViewModel to our composable screen.
            TodoScreen(viewModel = viewModel)
        }
    }
}
