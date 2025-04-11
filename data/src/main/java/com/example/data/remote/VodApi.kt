package com.example.data.remote

import com.example.data.remote.dto.AuidDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface VodApi {

    @GET("/v3/auid")
    @Headers("x-api-key: YW5va2ktcHJvZC1hcHA.k7pj7XeKY0yD8moz")
    suspend fun getTodo(@Query("deviceId") deviceId: String): Response<AuidDto>
}


