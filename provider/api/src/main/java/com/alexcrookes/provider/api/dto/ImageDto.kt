package com.alexcrookes.provider.api.dto

import com.alexcrookes.core.entities.RemoteImage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageDto(
	@SerialName("type") val type: String,
	@SerialName("uri") val uri: String,
	@SerialName("resource_url") val resourceUrl: String,
	@SerialName("uri150") val uri150: String,
	@SerialName("width") val width: Int,
	@SerialName("height") val height: Int,
)
