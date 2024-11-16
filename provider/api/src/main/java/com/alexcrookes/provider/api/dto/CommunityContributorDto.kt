package com.alexcrookes.provider.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommunityContributorDto(
    @SerialName("username") val userName: String,
    @SerialName("resource_url") val resourceUrl: String
)
