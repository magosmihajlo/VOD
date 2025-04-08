package com.example.vod

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.vod.data.repository.TodoRepositoryImpl
import com.example.vod.presentation.ui.TodoScreen
import com.example.vod.presentation.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Pass the context (this) to the repository.
        val repository = TodoRepositoryImpl(this)
        val viewModel = TodoViewModel(repository)

        setContent {
            TodoScreen(viewModel = viewModel)
        }
    }
}
