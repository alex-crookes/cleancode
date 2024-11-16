package com.alexcrookes.provider.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FormatDto(
	@SerialName("name") val name: String,
	@SerialName("qty") val quantity: String,
	@SerialName("descriptions") val descriptions: List<String> = emptyList()
)
