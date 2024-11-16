package com.alexcrookes.provider.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserStatusDto(
    @SerialName("in_wantlist") val wanted: Boolean,
    @SerialName("in_collection") val owned: Boolean
)
