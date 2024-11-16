package com.alexcrookes.provider.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommunityRatingDto(
    @SerialName("count") val ratingCount: Int,
    @SerialName("average") val averageRating: Double
)
