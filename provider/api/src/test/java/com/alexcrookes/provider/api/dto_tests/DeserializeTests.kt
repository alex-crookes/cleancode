package com.alexcrookes.provider.api.dto_tests

import com.alexcrookes.provider.fileio.FileIO
import com.alexcrookes.provider.fileio.FileIOImplementation
import kotlinx.serialization.KSerializer

abstract class DeserializeTests {
	private val fileIO: FileIO = FileIOImplementation()

	fun <T : Any> loadAndDeserialize(fileName: String, deserializer: KSerializer<T>, moreTests: ((T) -> Unit)) {
		val dto = fileIO.readClassFromResources(fileName, deserializer)

		val sut = dto ?: throw Exception("Expected not to be null")
		moreTests(sut)
	}
}
