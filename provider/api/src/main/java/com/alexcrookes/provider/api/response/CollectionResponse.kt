package com.alexcrookes.provider.api.response

import com.alexcrookes.provider.api.dto.CollectionReleaseDto
import com.alexcrookes.provider.api.dto.PaginationDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionResponse(
	@SerialName("pagination") val pagination: PaginationDto,
	@SerialName("releases") val releases: List<CollectionReleaseDto>
)
