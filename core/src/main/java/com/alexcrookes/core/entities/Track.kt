package com.alexcrookes.core.entities

data class Track(
	val title: String,
	val duration: String,
	val extraArtists: List<String>,
	val position: String,
	val type: TrackType
)
