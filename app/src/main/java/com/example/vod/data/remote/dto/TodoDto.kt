/*
This file defines how the JSON data from the server is represented in our app
*/


package com.example.vod.data.remote.dto
import com.google.gson.annotations.SerializedName

/*
The @SerializedName annotations ensure that even if your variable names differ from the JSON keys, the data is mapped correctly
*/

data class TodoDto(
    @SerializedName("value") val valueString: String // Or use a more meaningful name like "deviceId" if it makes sense
)
