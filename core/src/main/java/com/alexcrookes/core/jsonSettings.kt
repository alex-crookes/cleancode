package com.alexcrookes.core

import kotlinx.serialization.json.Json

val coreJsonSettings = Json {
	isLenient = true
	encodeDefaults = true
	ignoreUnknownKeys = true
	prettyPrint = false
}
