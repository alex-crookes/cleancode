package com.alexcrookes.provider.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The MasterSearchResultDto is a truncated version of the MasterReleaseDro
 *
 *
 * @see MasterReleaseDto
 */
@Serializable
data class SearchResultDto (
    @SerialName("master_id") val masterId: Long? = null,
    @SerialName("master_url") val masterUrl: String? = null,
    @SerialName("country") val country: String? = null,
    @SerialName("year") val year: String? = null,

    @SerialName("formats") val formats: List<ReleaseFormatDto>? = emptyList(),
    @SerialName("format_quantity") val formatQuantity: Int? = 1,
    @SerialName("format") val format: List<String>? = emptyList(),
    @SerialName("label") val labels: List<String>? = emptyList(),
    @SerialName("type") val type: String,
    @SerialName("genre") val genres: List<String>? = emptyList(),
    @SerialName("style") val styles: List<String>? = emptyList(),

    @SerialName("id") val id: Long,
    @SerialName("barcode") val barcodes: List<String>? = emptyList(),
    @SerialName("uri") val relativeUrl: String,
    @SerialName("catno") val catalogNumber: String? = null,
    @SerialName("title") val title: String,
    @SerialName("thumb") val thumbnailImage: String,
    @SerialName("cover_image") val coverImage: String,
    @SerialName("resource_url") val resourceUrl: String,
    @SerialName("user_data") val userStatus: UserStatusDto,
    @SerialName("community") val communityStatus: CommunityReleaseStatusDto? = null
)
