package com.alexcrookes.repository.catalog.fakes

import com.alexcrookes.core.coreJsonSettings
import com.alexcrookes.provider.local_preferences.Preferences
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.KSerializer

class FakePreferences: Preferences {
	private val prefs = mutableMapOf<String, String>()

	override suspend fun <T> writeWithSerialization(
		serializer: KSerializer<T>,
		keyName: String,
		value: T,
	) {
		val json = coreJsonSettings.encodeToString(serializer, value)
		prefs[keyName] = json
	}

	override fun <T> writeWithSerializationSync(
		serializer: KSerializer<T>,
		keyName: String,
		value: T,
	) {
		runBlocking { writeWithSerialization(serializer, keyName, value) }
	}

	override suspend fun <T> readFromSerialization(
		serializer: KSerializer<T>,
		keyName: String,
	): T? {
		prefs[keyName]?.let {
			return coreJsonSettings.decodeFromString(serializer, it)
		}

		return null
	}

	override fun <T> readFromSerializationSync(serializer: KSerializer<T>, keyName: String): T? {
		return runBlocking { readFromSerialization(serializer, keyName)  }
	}

	override suspend fun clearKey(keyName: String) {
		prefs.remove(keyName)
	}

	override fun clearKeySync(keyName: String) {
		prefs.remove(keyName)
	}

	override suspend fun clearAll() {
		prefs.clear()
	}

	override fun clearAllSync() {
		prefs.clear()
	}

}
