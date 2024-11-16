package com.alexcrookes.repository.catalog.dto_translations

import com.alexcrookes.core.entities.Video
import com.alexcrookes.provider.api.dto.ReleaseVideoDto
import com.alexcrookes.repository.catalog.extension.asVideo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class VideoDtoTests {

	@Test
	fun convert_VideoDtoToVideo() {
		val dto = ReleaseVideoDto(
			url = "https://host.com/video/1234",
			title = "Video Title",
			description = "Described video",
			duration = 300,
			embed = false
		)

		val sut = dto.asVideo

		assertNotNull(sut)

		require (sut is Video.Other) {
			throw AssertionError("Expected Other")
		}

		assertEquals(dto.url, sut.url)
		assertEquals(dto.description, sut.description)
		assertEquals(dto.embed, sut.embed)
		assertEquals(dto.title, sut.title)
		assertEquals(dto.duration, sut.duration)
	}

	@Test
	fun convertYouTubeToVideo() {
		val dto = ReleaseVideoDto(
			url = "https://www.youtube.com/watch?v=n3jMY98KIEw",
			title = "Never Gonna Give You Up",
			description = "Described video",
			duration = 300,
			embed = false
		)

		val sut = dto.asVideo

		assertNotNull(sut)

		require (sut is Video.YouTube) {
			throw AssertionError("Expected Other")
		}

		assertEquals("n3jMY98KIEw", sut.id)
		assertEquals(dto.url, sut.url)
		assertEquals(dto.description, sut.description)
		assertEquals(dto.embed, sut.embed)
		assertEquals(dto.title, sut.title)
		assertEquals(dto.duration, sut.duration)
	}
}
