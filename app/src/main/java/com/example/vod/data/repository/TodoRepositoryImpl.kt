package com.example.vod.data.repository

import android.content.Context
import android.util.Log
import com.example.vod.data.remote.RetrofitInstance
import com.example.vod.data.remote.dto.TodoDto
import com.example.vod.domain.model.Todo
import com.example.vod.domain.repository.TodoRepository
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class TodoRepositoryImpl(private val context: Context) : TodoRepository {

    override suspend fun getTodo(): Todo {
        val advertisingId = getAdvertisingId(context)
        Log.d("AdvertisingID", "Using Advertising ID for API call: $advertisingId")

        try {
            // This API call now correctly returns Response<String>
            val response = RetrofitInstance.api.getTodo(advertisingId)

            return if (response.isSuccessful) {
                // Get the raw String from the response body
                val responseBodyString: String? = response.body()

                if (!responseBodyString.isNullOrEmpty()) {
                    Log.d("ServerResponse", "API Raw String Response: $responseBodyString")

                    // Step 1: Manually create TodoDto using the String response
                    val todoDto = TodoDto(valueString = responseBodyString)
                    Log.d("DataMapping", "Manually created DTO: $todoDto")

                    // Step 2: Map the manually created TodoDto to the Todo domain model
                    val todoDomain = Todo(value = todoDto.valueString)
                    Log.d("DataMapping", "Mapped to Domain Model: $todoDomain")

                    todoDomain // Return the domain model

                } else {
                    // Handle the case where the response body is null or empty string
                    Log.e("ServerResponse", "API Success but Response body is null or empty.")
                    Todo(value = "") // Return a default/empty domain model
                }
            } else {
                // Handle unsuccessful API response (e.g., 4xx, 5xx errors)
                val errorBody = response.errorBody()?.string() ?: "No error body"
                Log.e("ServerResponse", "API Error: Code=${response.code()}, Message=${response.message()}, Body='$errorBody'")
                Todo(value = "") // Return a default/empty domain model
            }
        } catch (e: IOException) {
            Log.e("TodoRepository", "Network IO Exception during API call", e)
            return Todo(value = "") // Return default on network errors
        } catch (e: Exception) {
            Log.e("TodoRepository", "Generic Exception during API call", e)
            return Todo(value = "") // Return default on other errors
        }
    }

    private suspend fun getAdvertisingId(context: Context): String {
        return withContext(Dispatchers.IO) {
            try {
                val info = AdvertisingIdClient.getAdvertisingIdInfo(context)
                val id = info?.id ?: ""
                Log.d("AdvertisingID", "Fetched Advertising ID: $id")
                id
            } catch (e: Exception) {
                // Log different types of exceptions specifically if needed
                Log.e("AdvertisingID", "Failed to fetch Advertising ID", e)
                "" // Return empty string on error
            }
        }
    }
}