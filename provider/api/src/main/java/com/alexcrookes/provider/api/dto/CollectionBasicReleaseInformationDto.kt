package com.alexcrookes.provider.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionBasicReleaseInformationDto(
	@SerialName("id") val id: Long,
	@SerialName("master_id") val masterId: Long,
	@SerialName("master_url") val masterUrl: String? = null,
	@SerialName("resource_url") val resourceUrl: String,
	@SerialName("thumb") val thumbnail: String,
	@SerialName("cover_image") val coverImage: String,
	@SerialName("title") val title: String,
	@SerialName("year") val year: Int,
	@SerialName("formats") val formats: List<ReleaseFormatDto>,
	@SerialName("artists") val artists: List<ArtistDto>,
	@SerialName("labels") val labels: List<EntityDto>,
	@SerialName("genres") val genres: List<String>,
	@SerialName("styles") val styles: List<String>
)
