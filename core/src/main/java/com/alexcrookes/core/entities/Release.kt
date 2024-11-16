package com.alexcrookes.core.entities

data class Release(
	val id: Long,
	val title: String,
	val description: String,
	val artists: List<String>,
	val year: Int,
	val uri: String,
	val resourceUrl: String,
	val genres: List<String>,
	val styles: List<String>,
	val images: List<RemoteImage>,
	val tracks: List<Track>,
	val videos: List<Video>,
	val community: Community
)
