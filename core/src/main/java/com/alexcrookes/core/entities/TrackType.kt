package com.alexcrookes.core.entities

enum class TrackType {
	Unknown,
	Track;

	companion object {
		fun fromApiName(type: String): TrackType = when (type.uppercase()) {
			"TRACK" -> Track
			else -> Unknown
		}
	}
}
