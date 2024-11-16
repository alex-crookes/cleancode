package com.alexcrookes.provider.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommunityDto(
	@SerialName("have") val have: Int,
	@SerialName("want") val want: Int,
	@SerialName("rating") val rating: CommunityRatingDto,
	@SerialName("submitter") val submitter: CommunityContributorDto,
	@SerialName("contributors") val contributors: List<CommunityContributorDto>,
	@SerialName("data_quality") val dataQuality: String,
	@SerialName("status") val status: String,
)
