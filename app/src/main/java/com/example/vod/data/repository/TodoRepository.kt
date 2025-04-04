/*
This interface defines a function getTodos() that will return a list of to-dos (as domain models).
*/

package com.example.vod.data.repository

import com.example.vod.domain.model.Todo

interface TodoRepository {
    suspend fun getTodos(): List<Todo>
}
