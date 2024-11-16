package com.alexcrookes.cache.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CachedEntity<T> (
	@SerialName("cacheExpires")
	val cacheExpires: Long,
	@SerialName("data")
	val data: T
)
