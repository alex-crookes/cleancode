package com.alexcrookes.provider.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The Master release is the over-arching "Master" version of an single release by an artist. The
 * implication here is that the release may be on multiple formats, released at different times
 * (i.e. re-pressings, remasters etc) and even on alternate labels
 */
@Serializable
data class MasterReleaseDto(
	@SerialName("id") val id: Long,
	@SerialName("status") val status: String,
	@SerialName("year") val year: Int,
	@SerialName("resource_url") val resourceUrl: String,
	@SerialName("uri") val permalink: String,
	@SerialName("artists") val artists: List<ArtistDto>,
	@SerialName("artists_sort") val artistsSortName: String,
	@SerialName("labels") val labels: List<EntityDto>,
	@SerialName("series") val series: List<String>,
	@SerialName("companies") val companies: List<EntityDto>,
	@SerialName("data_quality") val dataQuality: String,
	@SerialName("community") val community: ReleaseCommunityMetaDto,
	@SerialName("format_quantity") val formatQuantity: Int,
	@SerialName("formats") val formats: List<ReleaseFormatDto>,
	@SerialName("date_added") val dateAdded: String,
	@SerialName("date_changed") val dateChanged: String,
	@SerialName("num_for_sale") val numberForSale: Int,
	@SerialName("lowest_price") val lowestPrice: Double,
	@SerialName("master_id") val masterId: Long,
	@SerialName("master_url") val masterUrl: String,
	@SerialName("title") val title: String,
	@SerialName("country") val country: String,
	@SerialName("released") val released: String,
	@SerialName("notes") val notes: String,
	@SerialName("released_formatted") val releasedFormatted: String,
	@SerialName("identifiers") val identifiers: List<ReleaseIdentifierDto>,
	@SerialName("videos") val videos: List<ReleaseVideoDto>,
	@SerialName("genres") val genres: List<String>,
	@SerialName("styles") val styles: List<String>,
	@SerialName("tracklist") val trackList: List<ReleaseTrackDto>,
	@SerialName("extraartists") val extraArtists: List<ArtistDto>,
	@SerialName("images") val images: List<ReleaseImageDto>,
	@SerialName("thumb") val thumbnail: String,
	@SerialName("estimated_weight") val estimatedWeight: Int,
	@SerialName("blocked_from_sale") val blockedFromSale: Boolean
)
