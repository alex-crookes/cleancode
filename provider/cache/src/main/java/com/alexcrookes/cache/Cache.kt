package com.alexcrookes.cache

import kotlinx.serialization.KSerializer

interface Cache {
	/**
	 * Stores a Serializable object in the cache
	 *
	 * @param keyName - Name of key to store at
	 * @param expiresInMillis - Number of milliseconds in the future the cache will expire at
	 * @param data<T> Data of type T to store
	 * @param serializer for the data
	 */
	suspend fun <T> storeUntil(keyName: String, expiresInMillis: Long, data: T, serializer: KSerializer<T>)

	/**
	 * Retrieves the data from cache
	 *
	 * @param keyName = cache key to retrieve
	 * @param serializer - Serializer to extract the data with
	 */
	suspend fun <T> retrieve(keyName: String, serializer: KSerializer<T>): T?

	/**
	 * remove the key from the cache
	 *
	 * @param keyName
	 */
	suspend fun clear(keyName: String)
}
