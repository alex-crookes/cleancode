package com.alexcrookes.core.entities

data class ListRelease(
	val id: Long,
	val masterId: Long,
	val resourceUrl: String,
	val thumbnail: String,
	val coverImage: String,
	val title: String,
	val year: Int,
	val formats: List<String>,
	val artists: List<String>,
	val labels: List<String>,
	val genres: List<String>,
	val styles: List<String>
)
