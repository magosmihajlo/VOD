package com.example.data.repository

import android.content.Context
import android.util.Log
import com.example.data.remote.RetrofitInstance
import com.example.data.remote.dto.mapToDomain
import com.example.domain.model.AuidModel
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class VodRepositoryImpl(private val context: Context) : com.example.domain.repository.VodRepository {

    override suspend fun getVod(): AuidModel {
        val advertisingId = getAdvertisingId(context)
        Log.d("AdvertisingID", "Using Advertising ID for API call: $advertisingId")

        try {
            val response = RetrofitInstance.api.getTodo(advertisingId)

            return if (response.isSuccessful) {
                val auidDto = response.body()
                if (auidDto != null) {
                    Log.d("ServerResponse", "API Success with DTO: $auidDto")
                    auidDto.mapToDomain()
                    //mapToDomain(vodDto)
                } else {
                    Log.e("ServerResponse Null", "API Success but Response body is null.")
                    AuidModel(value = "")
                }
            } else {
                val errorBody = response.errorBody()?.string() ?: "No error body"
                Log.e("ServerResponse API err", "API Error: Code=${response.code()}, Message=${response.message()}, Body='$errorBody'")
                AuidModel(value = "")
            }
        } catch (e: IOException) {
            Log.e("TodoRepository Network", "Network IO Exception during API call", e)
            return AuidModel(value = "")
        } catch (e: Exception) {
            Log.e("TodoRepository Generic", "Generic Exception during API call", e)
            return AuidModel(value = "")
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
                Log.e("Bad AdvertisingID", "Failed to fetch Advertising ID", e)
                ""
            }
        }
    }
}
