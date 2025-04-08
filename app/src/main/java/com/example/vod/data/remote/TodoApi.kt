/*
This is an interface that Retrofit uses to perform network calls.

The @GET("/todos") annotation specifies the endpoint. When you call getTodos()
Retrofit makes a GET request to https://jsonplaceholder.typicode.com/todos (the base URL is set elsewhere).
*/


package com.example.vod.data.remote

import com.example.vod.data.remote.dto.TodoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TodoApi {

    @GET("v3/auid")
    @Headers("x-api-key: YW5va2ktcHJvZC1hcHA.k7pj7XeKY0yD8moz")
    suspend fun getTodo(@Query("deviceId") deviceId: String): Response<String>
}


