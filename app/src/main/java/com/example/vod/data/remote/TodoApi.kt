/*
This is an interface that Retrofit uses to perform network calls.

The @GET("/todos") annotation specifies the endpoint. When you call getTodos()
Retrofit makes a GET request to https://jsonplaceholder.typicode.com/todos (the base URL is set elsewhere).
*/


package com.example.vod.data.remote

import com.example.vod.data.remote.dto.TodoDto
import retrofit2.Response
import retrofit2.http.GET

interface TodoApi {

    @GET("/todos")
    suspend fun getTodos(): Response<List<TodoDto>>
}
