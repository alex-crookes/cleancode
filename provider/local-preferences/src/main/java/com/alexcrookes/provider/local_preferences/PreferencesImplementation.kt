package com.alexcrookes.provider.local_preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import javax.inject.Inject

class PreferencesImplementation @Inject constructor(
	private val context: Context
) : com.alexcrookes.provider.local_preferences.Preferences {


	private val json = Json {
		ignoreUnknownKeys = true
		isLenient = true
		encodeDefaults = true
	}

	private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
		name = "spyndroid_preferences"
	)

	// endregion

	// region Implementation LocalPreferences

	override suspend fun <T> writeWithSerialization(
		serializer: KSerializer<T>,
		keyName: String,
		value: T,
	) {
		val key = stringPreferencesKey(keyName)
		val serializedValue = json.encodeToString(serializer, value)
		context.dataStore.edit { it[key] = serializedValue }
	}

	override fun <T> writeWithSerializationSync(
		serializer: KSerializer<T>,
		keyName: String,
		value: T,
	) {
		runBlocking {
			writeWithSerialization(serializer, keyName, value)
		}
	}

	override suspend fun <T> readFromSerialization(
		serializer: KSerializer<T>,
		keyName: String,
	): T? {
		val key = stringPreferencesKey(keyName)
		return context.dataStore.data.map {
			val serialized = it[key] ?: return@map null

			return@map json.decodeFromString(serializer, serialized)
		}.firstOrNull()
	}

	override fun <T> readFromSerializationSync(
		serializer: KSerializer<T>,
		keyName: String,
	): T? {
		return runBlocking {
			readFromSerialization(serializer, keyName)
		}
	}

	override suspend fun clearKey(keyName: String) {
		val key = stringPreferencesKey(keyName)
		context.dataStore.edit { it.remove(key) }
	}

	override fun clearKeySync(keyName: String) {
		runBlocking { clearKey(keyName) }
	}

	override suspend fun clearAll() {
		context.dataStore.edit { it.clear() }
	}

	override fun clearAllSync() {
		runBlocking { clearAll() }
	}

	// endregion
}
