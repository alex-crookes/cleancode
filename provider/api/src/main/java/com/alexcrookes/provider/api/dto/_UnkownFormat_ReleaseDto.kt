package com.alexcrookes.provider.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class _UnkownFormat_ReleaseDto(
	@SerialName("id") val id: Long,
	@SerialName("main_release") val mainRelease: Long,
	@SerialName("most_recent_release") val mostRecentRelease: Long,
	@SerialName("resource_url") val resourceUrl: String,
	@SerialName("uri") val permalink: String,
	@SerialName("versions_url") val versionsUrl: String,
	@SerialName("main_release_url") val mainReleaseUrl: String,
	@SerialName("most_recent_release_url") val mostRecentReleaseUrl: String,
	@SerialName("num_for_sale") val forSaleCount: Int,
	@SerialName("lowest_price") val lowestPrice: Double, // need to understand currency
	@SerialName("images") val images: List<ReleaseImageDto>,
	@SerialName("genres") val genres: List<String>,
	@SerialName("styles") val styles: List<String>,
	@SerialName("year") val year: Long,
	@SerialName("tracklist") val tracks: List<ReleaseTrackDto>,
	@SerialName("artists") val artists: List<ArtistDto>,
	@SerialName("title") val title: String,
	@SerialName("notes") val notes: String,
	@SerialName("data_quality") val dataQuality: String,
	@SerialName("videos") val videos: List<ReleaseVideoDto>
)
