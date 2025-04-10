package com.example.data.remote.dto

import com.google.gson.*
import java.lang.reflect.Type

class VodDtoDeserializer : JsonDeserializer<VodDto> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): VodDto {
        // The response is just a string, like: "Hello"
        val rawValue = json.asString
        return VodDto(valueString = rawValue)
    }
}
