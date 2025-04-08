/*
The object ensures that only one Retrofit instance exists.

It sets the base URL and creates the API service (an instance of TodoApi) that other parts of the app use to make network requests.
*/


package com.example.vod.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitInstance {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        // Uncomment the next line to enable logging of network requests/responses
        // .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://stream-stage.aistrm.net:5200/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(client)
            .build()
    }

    val api: TodoApi by lazy {
        retrofit.create(TodoApi::class.java)
    }
}


