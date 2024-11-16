package com.alexcrookes.provider.api.dto

import com.alexcrookes.provider.api.dto.CommunityContributorDto
import com.alexcrookes.provider.api.dto.CommunityRatingDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReleaseCommunityMetaDto(
	@SerialName("have") val have: Int,
	@SerialName("want") val want: Int,
	@SerialName("rating") val rating: CommunityRatingDto,
	@SerialName("submitter") val submitter: CommunityContributorDto,
	@SerialName("contributors") val contributors: List<CommunityContributorDto>,
	@SerialName("data_quality") val dataQuality: String,
	@SerialName("status") val status: String
)
