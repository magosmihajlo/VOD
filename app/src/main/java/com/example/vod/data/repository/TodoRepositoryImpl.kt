/*
This class implements the repository interface.

It makes a network request via Retrofit to fetch the data.

If the response is successful, it maps each TodoDto into a domain model (Todo), which is what the rest of the app will use.

If the request fails, it returns an empty list.
 */

package com.example.vod.data.repository

import com.example.vod.data.remote.RetrofitInstance
import com.example.vod.data.remote.dto.TodoDto
import com.example.vod.domain.model.Todo
import com.example.vod.domain.repository.TodoRepository

class TodoRepositoryImpl : TodoRepository {

    override suspend fun getTodos(): List<Todo> {
        val response = RetrofitInstance.api.getTodos()
        return if (response.isSuccessful) {
            response.body()
                ?.map { it.toDomain() }
                .orEmpty()
        } else {
            emptyList()
        }
    }

    // Mapping from DTO to Domain model
    private fun TodoDto.toDomain(): Todo {
        return Todo(
            userId = userId,
            id = id,
            title = title,
            completed = completed
        )
    }
}
