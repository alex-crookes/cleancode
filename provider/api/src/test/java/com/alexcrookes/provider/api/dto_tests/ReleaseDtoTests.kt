package com.alexcrookes.provider.api.dto_tests

import com.alexcrookes.provider.api.dto.ReleaseDto
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class ReleaseDtoTests : DeserializeTests() {
	val first = "/dto/release-249504-Code200.json"

	@Test
	fun test_Release_RollWithIt() {
		loadAndDeserialize(first, ReleaseDto.serializer()) { sut ->
			assertNotNull(sut)
			assertEquals("Never Gonna Give You Up", sut.title)
			assertEquals(249504, sut.id)
			assertEquals(96559, sut.masterReleaseId)
			assertEquals(13, sut.identifiers.size)
			assertEquals(1, sut.artists.size)
			assertEquals("Rick Astley", sut.artists.first().name)
			assertEquals(4, sut.images.size)
			assertEquals(17, sut.videos.size)
		}
	}
}
