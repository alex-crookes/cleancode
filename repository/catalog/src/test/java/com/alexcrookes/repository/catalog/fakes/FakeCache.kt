package com.alexcrookes.repository.catalog.fakes

import com.alexcrookes.cache.Cache
import kotlinx.serialization.KSerializer

class FakeCache : Cache {
	override suspend fun <T> storeUntil(
		keyName: String,
		expiresInMillis: Long,
		data: T,
		serializer: KSerializer<T>,
	) {
		TODO("Not yet implemented")
	}

	override suspend fun <T> retrieve(keyName: String, serializer: KSerializer<T>): T? {
		TODO("Not yet implemented")
	}

	override suspend fun clear(keyName: String) {
		TODO("Not yet implemented")
	}

}
