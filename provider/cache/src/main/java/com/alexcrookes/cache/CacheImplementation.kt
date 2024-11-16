package com.alexcrookes.cache

import com.alexcrookes.cache.model.CachedEntity
import com.alexcrookes.provider.local_preferences.Preferences
import kotlinx.serialization.KSerializer
import javax.inject.Inject
import kotlin.math.abs

class CacheImplementation @Inject constructor(private val prefs: Preferences) : Cache {
	override suspend fun <T> storeUntil(
		keyName: String,
		expiresInMillis: Long,
		data: T,
		serializer: KSerializer<T>,
	) {
		val expiresIn = System.currentTimeMillis() + abs(expiresInMillis)
		val cacheData = CachedEntity(
			cacheExpires = expiresIn,
			data = data
		)

		prefs.writeWithSerialization(
			serializer = CachedEntity.serializer(serializer),
			keyName = keyName,
			value = cacheData
		)
	}

	override suspend fun <T> retrieve(keyName: String, serializer: KSerializer<T>): T? {
		val cachedKSerializer = CachedEntity.serializer(serializer)
		val cacheHit = prefs.readFromSerialization(cachedKSerializer, keyName)

		requireNotNull(cacheHit) { return null }

		require (cacheHit.cacheExpires > System.currentTimeMillis()) {
			clear(keyName)
			return null
		}

		return cacheHit.data
	}

	override suspend fun clear(keyName: String) {
		prefs.clearKey(keyName)
	}
}
