package com.alexcrookes.provider.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReleaseIdentifierDto(
    @SerialName("type") val type: String,
    @SerialName("value") val value: String,
    @SerialName("description") val description: String,
)
