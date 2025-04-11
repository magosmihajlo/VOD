package com.example.data.remote.dto

import com.example.domain.model.AuidModel
import com.google.gson.annotations.SerializedName

data class AuidDto(
    @SerializedName("value") val valueString: String
)

fun AuidDto.mapToDomain(): AuidModel {
    return AuidModel(value = valueString)
}

/*fun mapToDomain(dto: VodDto): VodModel {
    return VodModel(value = dto.valueString)
}*/