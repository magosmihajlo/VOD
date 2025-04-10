package com.example.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.presentation.viewmodel.VodViewModel

@Composable
fun VodScreen(viewModel: VodViewModel) {
    val vod by viewModel.vod.collectAsState()

    Column {
        Text(text = "API response:")
        Text(text = vod.value)
    }
}