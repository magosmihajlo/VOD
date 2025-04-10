package com.example.data.repository

import android.content.Context
import android.util.Log
import com.example.data.remote.RetrofitInstance
import com.example.data.remote.dto.VodDto

import com.example.domain.model.VodModel
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class VodRepositoryImpl(private val context: Context) : com.example.domain.repository.VodRepository {

    override suspend fun getVod(): VodModel {
        val advertisingId = getAdvertisingId(context)
        Log.d("AdvertisingID", "Using Advertising ID for API call: $advertisingId")

        try {
            val response = RetrofitInstance.api.getTodo(advertisingId)

            val vodDto = response.body()

            return if (response.isSuccessful) {
                //val todoDto = response.body()
                if (vodDto != null) {
                    Log.d("ServerResponse", "API Success with DTO: $vodDto")
                    mapToDomain(vodDto)
                } else {
                    Log.e("ServerResponse Null", "API Success but Response body is null.")
                    VodModel(value = "")
                }
            } else {
                val errorBody = response.errorBody()?.string() ?: "No error body"
                Log.e("ServerResponse API err", "API Error: Code=${response.code()}, Message=${response.message()}, Body='$errorBody'")
                VodModel(value = "")
            }
        } catch (e: IOException) {
            Log.e("TodoRepository Network", "Network IO Exception during API call", e)
            return VodModel(value = "")
        } catch (e: Exception) {
            Log.e("TodoRepository Generic", "Generic Exception during API call", e)
            return VodModel(value = "")
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

    private fun mapToDomain(dto: VodDto): VodModel {
        return VodModel(value = dto.valueString)
    }
}
