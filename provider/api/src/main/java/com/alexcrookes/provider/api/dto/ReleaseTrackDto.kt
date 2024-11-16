package com.alexcrookes.provider.api.dto

import com.alexcrookes.core.entities.Track
import com.alexcrookes.core.entities.TrackType
import com.alexcrookes.provider.api.dto.ArtistDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReleaseTrackDto(
	@SerialName("position") val position: String,
	@SerialName("type_") val type: String,
	@SerialName("title") val title: String,
	@SerialName("extraartists") val extraArtists: List<ArtistDto> = emptyList(),
	@SerialName("duration") val duration: String
)
