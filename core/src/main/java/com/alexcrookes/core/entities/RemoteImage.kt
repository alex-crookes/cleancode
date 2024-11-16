package com.alexcrookes.core.entities

sealed class RemoteImage(val display: String) {
	data class Primary(
		val uri: String,
		val resourceUrl: String,
		val uri150: String,
		val width: Int,
		val height: Int
	) : RemoteImage(display = uri)

	data class Secondary(
		val uri: String,
		val resourceUrl: String,
		val uri150: String,
		val width: Int,
		val height: Int
	) : RemoteImage(display = uri)
}
