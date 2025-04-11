package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.VodRepository
import com.example.domain.model.AuidModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VodViewModel(
    private val repository: VodRepository
) : ViewModel() {

    private val _vod = MutableStateFlow(
        AuidModel("")
    )
    val vod = _vod.asStateFlow()

    init {
        fetchVod()
    }

    private fun fetchVod() {
        viewModelScope.launch {
            val vodFromRepo = repository.getVod()
            _vod.value = vodFromRepo
        }
    }
}