package com.alexcrookes.provider.api.dto_tests

import com.alexcrookes.provider.api.dto.ArtistDto
import com.alexcrookes.provider.api.dto._UnkownFormat_ReleaseDto
import com.alexcrookes.provider.api.dto.ReleaseImageDto
import com.alexcrookes.provider.api.dto.ReleaseTrackDto
import com.alexcrookes.provider.api.dto.ReleaseVideoDto
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class UnkownFormatReleaseDtoTests : DeserializeTests() {

	private val artistDto = "/dto/artist.dto.json"
	private val releaseImageDtp = "/dto/release_image.dto.json"
	private val releaseVideoDto = "/dto/release_video.dto.json"
	private val trackDto = "/dto/release_track.dto.json"
	private val releaseDto = "/dto/_unknownformat_release.dto.json"

	@Test
	fun test_ReleaseImageDtoDeserialized() {
		loadAndDeserialize(releaseImageDtp, ReleaseImageDto.serializer()) { sut ->
			assertEquals("primary", sut.type)
			assertEquals(600, sut.width)
			assertEquals(600, sut.height)
			assertNotNull(sut.thumbnail)
			assertNotNull(sut.masterImage)
			assertEquals(sut.masterImage, sut.otherMasterImage)
		}
	}

	@Test
	fun test_ReleaseVideoDtoDeserialized() {
		loadAndDeserialize(releaseVideoDto, ReleaseVideoDto.serializer()) { sut ->
			assertEquals("Just What I Needed", sut.title)
			assertEquals(226, sut.duration)
			assertTrue(sut.embed)
			assertNotNull(sut.description)
			assertNotNull(sut.url)
		}
	}

	@Test
	fun test_ArtistDtoDeserialized() {
		loadAndDeserialize(artistDto, ArtistDto.serializer()) { sut ->
			assertEquals("The Cars", sut.name)
			assertEquals(143225, sut.id)
			assertTrue(sut.resourceUrl.contains(sut.id.toString()))
			assertNotNull(sut.thumbnail)
			assertTrue(sut.thumbnail?.contains("i.discogs.com") ?: false)
			assertEquals("", sut.anv)
			assertEquals("", sut.join)
			assertEquals("", sut.role)
			assertEquals("", sut.tracks)
		}
	}

	@Test
	fun test_TrackDtoDeserialized() {
		loadAndDeserialize(trackDto, ReleaseTrackDto.serializer()) { sut ->
			assertEquals("A4", sut.position)
			assertEquals("track", sut.type)
			assertEquals("I'm In Touch With Your World", sut.title)
			assertEquals(1, sut.extraArtists.size)
			assertEquals("3:31", sut.duration)
		}
	}

	@Test
	fun test_ReleaseDtoDeserialized() {
		loadAndDeserialize(releaseDto, _UnkownFormat_ReleaseDto.serializer()) { sut ->
			assertEquals(69666, sut.id)
			assertTrue(sut.resourceUrl.contains(sut.id.toString()))
			assertEquals(190219, sut.mainRelease)
			assertTrue(sut.mainReleaseUrl.contains(sut.mainRelease.toString()))
			assertEquals(27190293, sut.mostRecentRelease)
			assertTrue(sut.mostRecentReleaseUrl.contains(sut.mostRecentRelease.toString()))
			assertTrue(sut.permalink.contains(sut.id.toString()))
			assertNotNull(sut.versionsUrl)
			assertTrue(sut.versionsUrl.contains(sut.id.toString()))

			assertEquals(1093, sut.forSaleCount)
			assertEquals(1.35, sut.lowestPrice, 0.0)
			assertEquals(1978, sut.year)

			assertEquals(8, sut.images.size)

			assertEquals(1, sut.genres.size)
			assertEquals("Rock", sut.genres.first())
			assertEquals(3, sut.styles.size)
			assertEquals("Power Pop", sut.styles.last())
			assertEquals(9, sut.tracks.size)
			val expectedTrackList = listOf("A1", "A2", "A3", "A4", "A5", "B1", "B2", "B3", "B4")
			assertEquals(expectedTrackList, sut.tracks.map { it.position })

			assertEquals(1, sut.artists.size)
			assertEquals("The Cars", sut.artists.first().name)

			assertEquals("The Cars", sut.title)
			assertNotNull(sut.notes)
			assertEquals("Correct", sut.dataQuality)

			assertEquals(9, sut.videos.size)
			val videoSut = sut.videos[3]
			assertEquals("I'm in Touch with Your World", videoSut.title)
			assertEquals(212, videoSut.duration)
		}
	}
}
