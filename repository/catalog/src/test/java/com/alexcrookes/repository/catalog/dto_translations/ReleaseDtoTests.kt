package com.alexcrookes.repository.catalog.dto_translations

import com.alexcrookes.core.extension.primaryImage
import com.alexcrookes.provider.api.dto.ReleaseDto
import com.alexcrookes.provider.fileio.FileIO
import com.alexcrookes.provider.fileio.FileIOImplementation
import com.alexcrookes.repository.catalog.extension.asRelease
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Assert.assertNotNull

class ReleaseDtoTests {
	private val fileIo: FileIO = FileIOImplementation()

	private val releaseDto = "/dto/release-249504-Code200.json"
	//val first =      "/dto/release-249504-Code200.json"

	@Test
	fun test_ReleaseDto_asEntity() {
		val data = fileIo.readClassFromResources(releaseDto, ReleaseDto.serializer())
		val dto = data ?: throw AssertionError("File is empty $releaseDto")

		val sut = dto.asRelease

		assertNotNull(sut)
		assertEquals(249504, sut.id)
		assertEquals("Rick Astley", sut.artists.first())
		assertEquals(2, sut.genres.size)
		assertEquals(1, sut.styles.size)
		assertEquals(2, sut.tracks.size)
		assertEquals("A", sut.tracks.first().position)
		assertEquals("B", sut.tracks.last().position)
		assertEquals(4, sut.images.size)
		assertNotNull(sut.primaryImage)
		assertEquals(17, sut.videos.size)

		// rating
		assertEquals(3.75, sut.community.rating.average, 0.00)
		assertEquals(200, sut.community.rating.count)
		assertEquals(3476, sut.community.ownedBy)
		assertEquals(534, sut.community.wantedBy)
	}
}
