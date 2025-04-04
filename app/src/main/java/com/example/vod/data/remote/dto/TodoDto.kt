/*
This file defines how the JSON data from the server is represented in our app
*/

package com.example.vod.data.remote.dto

import com.google.gson.annotations.SerializedName

/*
The @SerializedName annotations ensure that even if your variable names differ from the JSON keys, the data is mapped correctly
*/
data class TodoDto(

    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("completed") val completed: Boolean
)