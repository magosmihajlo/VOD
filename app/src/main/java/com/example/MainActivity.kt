package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.presentation.ui.VodScreen
import com.example.presentation.viewmodel.VodViewModel
import com.example.vod.di.Dependency

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dependency = Dependency()
        val repository = dependency.getVodRepository(this)
        val viewModel = VodViewModel(repository)

        setContent {
            VodScreen(viewModel = viewModel)
        }
    }
}
