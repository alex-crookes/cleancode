package com.alexcrookes.provider.api.response

import com.alexcrookes.provider.api.dto.PaginationDto
import com.alexcrookes.provider.api.dto.SearchResultDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
	@SerialName("pagination") val pagination: PaginationDto,
	@SerialName("results") val results: List<SearchResultDto>,
)
