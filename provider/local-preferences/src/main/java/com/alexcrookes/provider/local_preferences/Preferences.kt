package com.alexcrookes.provider.local_preferences

import com.alexcrookes.core.extension.Epoch
import kotlinx.serialization.KSerializer

interface Preferences {

	suspend fun <T> writeWithSerialization(
		serializer: KSerializer<T>, keyName: String, value: T,
	)

	fun <T> writeWithSerializationSync(
		serializer: KSerializer<T>, keyName: String, value: T,
	)

	suspend fun <T> readFromSerialization(
		serializer: KSerializer<T>, keyName: String,
	): T?

	fun <T> readFromSerializationSync(
		serializer: KSerializer<T>, keyName: String,
	): T?

	suspend fun clearKey(keyName: String)

	fun clearKeySync(keyName: String)

	suspend fun clearAll()

	fun clearAllSync()

	fun writeTimeStamp(keyName: String, timeStamp: Epoch)
	fun readTimeStamp(keyName: String): Epoch
}
