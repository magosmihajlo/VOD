package com.example.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.data.remote.dto.VodDto
import com.example.data.remote.dto.VodDtoDeserializer
import com.google.gson.GsonBuilder


object RetrofitInstance {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        // Uncomment the next line to enable logging of network requests/responses
        // .addInterceptor(loggingInterceptor)
        .build()

    private val gson = GsonBuilder()
        .registerTypeAdapter(VodDto::class.java, VodDtoDeserializer())
        .setLenient()
        .create()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://stream-stage.aistrm.net:5200") // ← Replace with real base URL
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val api: VodApi by lazy {
        retrofit.create(VodApi::class.java)
    }
}


