package com.alexcrookes.provider.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReleaseDto(
	@SerialName("id") val id: Long,
	@SerialName("status") val status: String,
	@SerialName("year") val year: Int,
	@SerialName("resource_url") val resourceUrl: String,
	@SerialName("uri") val uri: String,
	@SerialName("artists") val artists: List<ArtistDto>,
	@SerialName("artists_sort") val sortingName: String,
	@SerialName("series") val series: List<String> = emptyList(),
	@SerialName("labels") val labels: List<EntityDto> = emptyList(),
	@SerialName("companies") val companies: List<EntityDto> = emptyList(),
	@SerialName("formats") val formats: List<FormatDto> = emptyList(),
	@SerialName("data_quality") val dataQuality: String,
	@SerialName("community") val community: CommunityDto,
	@SerialName("format_quantity") val formatQuantity: Int,
	@SerialName("date_added") val dateAdded: String,
	@SerialName("date_changed") val dateChanged: String,
	@SerialName("num_for_sale") val copiesAvailable: Int,
	@SerialName("lowest_price") val lowestPrice: Double,
	@SerialName("master_id") val masterReleaseId: Long,
	@SerialName("master_url") val masterReleaseUrl: String,
	@SerialName("title") val title: String,
	@SerialName("country") val country: String,
	@SerialName("released") val releaseDate: String,
	@SerialName("notes") val notes: String,
	@SerialName("released_formatted") val releaseDateFormatted: String,
	@SerialName("identifiers") val identifiers: List<IdentifierDto>,
	@SerialName("videos") val videos: List<ReleaseVideoDto>,
	@SerialName("genres") val genres: List<String>,
	@SerialName("styles") val styles: List<String>,
	@SerialName("tracklist") val tracks: List<ReleaseTrackDto>,
	@SerialName("extraartists") val extraArtists: List<ArtistDto>,
	@SerialName("images") val images: List<ImageDto>,
	@SerialName("thumb") val thumbnail: String,
	@SerialName("estimated_weight") val estimatedWeightInGrams: Int,
	@SerialName("blocked_from_sale") val blockedFromSale: Boolean
)
