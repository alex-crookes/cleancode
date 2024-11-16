package com.alexcrookes.provider.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReleaseVideoDto(
    @SerialName("uri") val url: String,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("duration") val duration: Int,
    @SerialName("embed") val embed: Boolean
)
