package com.alexcrookes.provider.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionReleaseNotesDto(
    @SerialName("field_id") val fieldId: Int,
    @SerialName("value") val value: String
)
