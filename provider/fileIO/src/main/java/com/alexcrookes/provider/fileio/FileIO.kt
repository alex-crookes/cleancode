package com.alexcrookes.provider.fileio

import kotlinx.serialization.KSerializer

interface FileIO {
	fun readFileFromResources(filename: String): String?

	fun <T : Any> readClassFromResources(filename: String, deserializer: KSerializer<T>): T?
}
