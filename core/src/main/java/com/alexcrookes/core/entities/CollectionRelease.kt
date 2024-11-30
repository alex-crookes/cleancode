package com.alexcrookes.core.entities

data class CollectionRelease(
	val id: Long,
	val instanceId: Long,
	val dateAdded: Long, // EPOCH
	val rating: Double,
	val folder: Int,
	val listRelease: ListRelease
)
