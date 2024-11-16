package com.alexcrookes.provider.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtistDto(
    @SerialName("name") val name: String,
    @SerialName("id") val id: Long,
    @SerialName("resource_url") val resourceUrl: String,
    @SerialName("thumbnail_url") val thumbnail: String? = null, // Present when used in Release, but not in Tracks
    @SerialName("anv") val anv: String,
    @SerialName("join") val join: String,
    @SerialName("role") val role: String,
    @SerialName("tracks") val tracks: String
)
