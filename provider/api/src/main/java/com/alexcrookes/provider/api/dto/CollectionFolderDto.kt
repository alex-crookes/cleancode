package com.alexcrookes.provider.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionFolderDto(
	@SerialName("id")
	val id: Int,

	@SerialName("name")
	val name: String,

	@SerialName("count")
	val count: Int,

	@SerialName("resource_url")
	val resourceUrl: String
)
