package com.alexcrookes.provider.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReleaseFormatDto(
    @SerialName("name") val name: String,
    @SerialName("qty") val quantity: String,
    @SerialName("text") val text: String? = null,
    @SerialName("descriptions") val descriptions: List<String>
)
