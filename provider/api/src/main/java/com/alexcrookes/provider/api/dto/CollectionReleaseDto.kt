package com.alexcrookes.provider.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionReleaseDto(
	@SerialName("id") val id: Long,
	@SerialName("instance_id") val instanceId: Long,
	@SerialName("date_added") val date: String,
	@SerialName("rating") val rating: Double,
	@SerialName("basic_information") val basicInformation: CollectionBasicReleaseInformationDto,
	@SerialName("folder_id") val folder: Int,
	@SerialName("notes") val notes: List<CollectionReleaseNotesDto>? = emptyList()
)
