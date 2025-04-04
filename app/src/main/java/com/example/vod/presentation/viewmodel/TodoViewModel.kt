/*
The ViewModel is responsible for fetching data and holding UI state.

The ViewModel initializes by calling fetchTodos(), which uses the repository to fetch the to-dos.

It stores the list of to-dos in a MutableStateFlow so that the UI can observe changes and update automatically.

The ViewModel separates the UI from data-fetching logic.
 */

package com.example.vod.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vod.domain.repository.TodoRepository
import com.example.vod.domain.model.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TodoViewModel(
    private val repository: TodoRepository
) : ViewModel() {

    private val _todoList = MutableStateFlow<List<Todo>>(emptyList())
    val todoList = _todoList.asStateFlow()

    init {
        fetchTodos()
    }

    private fun fetchTodos() {
        viewModelScope.launch {
            val todos = repository.getTodos()
            _todoList.value = todos
        }
    }
}
