package com.alexcrookes.core.entities

data class Community(
	val wantedBy: Int,
	val ownedBy: Int,
	val rating: Rating
)
