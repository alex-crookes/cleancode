package com.alexcrookes.core.entities

sealed class Video (
	val url: String,
	val title: String,
	val duration: Int,
	val embed: Boolean,
	val description: String
) {
	data class YouTube(
		val id: String,
		private val _url: String,
		private val _title: String,
		private val _duration: Int,
		private val _embed: Boolean,
		private val _description: String
	) : Video(
		url = _url,
		title = _title,
		duration = _duration,
		embed = _embed,
		description = _description
	)
	data class Other(
		private val _url: String,
		private val _title: String,
		private val _duration: Int,
		private val _embed: Boolean,
		private val _description: String
	) : Video(
		url = _url,
		title = _title,
		duration = _duration,
		embed = _embed,
		description = _description
	)
}
