package com.alexcrookes.repository.catalog.dto_translations

import com.alexcrookes.core.entities.TrackType
import com.alexcrookes.provider.api.dto.ArtistDto
import com.alexcrookes.provider.api.dto.ReleaseTrackDto
import com.alexcrookes.repository.catalog.extension.asTrack
import org.junit.Assert
import org.junit.Test

private val artist = ArtistDto(
	name = "artist1",
	id = 1234,
	resourceUrl = "resource_url",
	thumbnail = "thumbnail",
	anv = "",
	join = "",
	role = "",
	tracks = ""
)

class TrackDtoTests {
	@Test
	fun test_convertTrackDto_toEntity() {
		val source = ReleaseTrackDto(
			title = "title",
			duration = "1:23",
			position = "A2",
			type = "tRacK"
		)

		val sut = source.asTrack

		Assert.assertNotNull(sut)
		Assert.assertEquals(source.title, sut.title)
		Assert.assertEquals(TrackType.Track, sut.type)
		Assert.assertEquals(0, sut.extraArtists.size)
	}

	@Test
	fun test_convertTrackDtoWithExtraAritists() {
		val source = ReleaseTrackDto(
			title = "title",
			duration = "1:23",
			position = "A2",
			type = "tRacK",
			extraArtists = listOf(
				artist,
				artist.copy(name = "artist2", id = 5678),
				artist.copy(name = "artist3", id = 91011)
			)
		)

		val sut = source.asTrack

		Assert.assertNotNull(sut)
		Assert.assertEquals(source.title, sut.title)
		Assert.assertEquals(TrackType.Track, sut.type)
		Assert.assertEquals(3, sut.extraArtists.size)
		Assert.assertEquals("artist3", sut.extraArtists.last())

	}

	@Test
	fun fails_convertNonTrackToEntity() {
		val source = ReleaseTrackDto(
			title = "title",
			duration = "1:23",
			position = "A2",
			type = "hidden"
		)

		val sut = source.asTrack

		Assert.assertNotNull(sut)
		Assert.assertEquals(source.title, sut.title)
		Assert.assertEquals(TrackType.Unknown, sut.type)
	}
}
