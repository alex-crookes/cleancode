package com.alexcrookes.provider.api.dto_tests

import com.alexcrookes.provider.api.dto.CollectionBasicReleaseInformationDto
import com.alexcrookes.provider.api.dto.CollectionReleaseDto
import com.alexcrookes.provider.api.dto.CollectionReleaseNotesDto
import com.alexcrookes.provider.api.response.CollectionResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CollectionDtoTests : DeserializeTests() {
	private val collectionReleaseNotes = "/dto/collection_release_notes.dto.json"
	private val basicInformation = "/dto/collection_release_basic_information.dto.json"
	private val basicInformationMissingMaster =
		"/dto/collection_release_basic_information-missing-master.dto.json"
	private val collectionRelease = "/dto/collection_release.dto.json"
	private val collectionReleaseWithNotes = "/dto/collection_release_with-notes.dto.json"

	private val collectionResponseGood = "/responses/collection_page1.results.json"

	@Test
	fun test_CollectionReleaseNotesDtoDeserialized() {
		loadAndDeserialize(collectionReleaseNotes, CollectionReleaseNotesDto.serializer()) { sut ->
			assertEquals(1, sut.fieldId)
			assertEquals("Poor (P)", sut.value)
		}
	}

	@Test
	fun test_BasicInformationDtoDeserialized() {
		loadAndDeserialize(
			basicInformation, CollectionBasicReleaseInformationDto.serializer()
		) { sut ->
			assertEquals(460238, sut.id)
			assertTrue(sut.resourceUrl.contains(sut.id.toString()))
			assertEquals(4322, sut.masterId)
			assertTrue(sut.masterUrl?.contains(sut.masterId.toString()) ?: false)
			assertNotNull(sut.thumbnail)
			assertNotNull(sut.coverImage)
			assertEquals("Tales Of Mystery And Imagination", sut.title)
			assertEquals(1976, sut.year)
			assertEquals(1, sut.formats.size)
			assertEquals("Vinyl", sut.formats.first().name)
			assertEquals(1, sut.artists.size)
			assertEquals("The Alan Parsons Project", sut.artists.first().name)
			assertEquals(1, sut.labels.size)
			assertEquals("6370 243", sut.labels.first().catalogueNumber)
			assertEquals(1, sut.genres.size)
			assertEquals(3, sut.styles.size)
			assertEquals("Prog Rock", sut.styles.last())
		}
	}

	@Test
	fun test_BasicInformationWithEmptyMasterDtoDeserialized() {
		loadAndDeserialize(
			basicInformationMissingMaster, CollectionBasicReleaseInformationDto.serializer()
		) { sut ->
			assertEquals(460238, sut.id)
			assertTrue(sut.resourceUrl.contains(sut.id.toString()))
			assertEquals(0, sut.masterId)
			assertNull(sut.masterUrl)
			assertNotNull(sut.thumbnail)
			assertNotNull(sut.coverImage)
			assertEquals("Tales Of Mystery And Imagination", sut.title)
			assertEquals(1976, sut.year)
			assertEquals(1, sut.formats.size)
			assertEquals("Vinyl", sut.formats.first().name)
			assertEquals(1, sut.artists.size)
			assertEquals("The Alan Parsons Project", sut.artists.first().name)
			assertEquals(1, sut.labels.size)
			assertEquals("6370 243", sut.labels.first().catalogueNumber)
			assertEquals(1, sut.genres.size)
			assertEquals(3, sut.styles.size)
			assertEquals("Prog Rock", sut.styles.last())
		}
	}

	@Test
	fun test_CollectionReleaseDtoDeserialized() {
		loadAndDeserialize(collectionRelease, CollectionReleaseDto.serializer()) { sut ->
			assertEquals(27888312, sut.id)
			assertTrue(sut.date.contains("2024"))
			assertEquals(1561681507, sut.instanceId)
			assertEquals(0.0, sut.rating, 0.0)
			assertNotNull(sut.basicInformation)
			assertEquals("Treasure", sut.basicInformation.title)
			assertEquals("Cocteau Twins", sut.basicInformation.artists.first().name)
			assertEquals(1, sut.basicInformation.styles.size)
			assertEquals(1, sut.folder)
			assertEquals(0, sut.notes?.size)
		}
	}

	@Test
	fun test_CollectionReleaseDtoWithNotesDeserialized() {
		loadAndDeserialize(collectionReleaseWithNotes, CollectionReleaseDto.serializer()) { sut ->
			assertEquals(27888312, sut.id)
			assertTrue(sut.date.contains("2024"))
			assertEquals(1561681507, sut.instanceId)
			assertEquals(0.0, sut.rating, 0.0)
			assertNotNull(sut.basicInformation)
			assertEquals("Treasure", sut.basicInformation.title)
			assertEquals("Cocteau Twins", sut.basicInformation.artists.first().name)
			assertEquals(1, sut.basicInformation.styles.size)
			assertEquals(1, sut.folder)
			assertNotNull(sut.notes)
			assertEquals(2, sut.notes?.lastOrNull()?.fieldId)
			assertEquals("Good (G)", sut.notes?.lastOrNull()?.value)
		}
	}


	@Test
	fun test_CollectionResults() {
		loadAndDeserialize(collectionResponseGood, CollectionResponse.serializer()) { sut ->
			assertNotNull(sut.pagination)
			assertNotNull(sut.releases)
			assertTrue(sut.releases.size > 2)
		}
	}
}
