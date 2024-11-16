package com.alexcrookes.provider.api

import com.alexcrookes.provider.api.dto.PaginationDto
import com.alexcrookes.provider.api.dto.PaginationUrlsDto
import com.alexcrookes.provider.api.dto_tests.DeserializeTests
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class PaginationTests: DeserializeTests() {
	private val paginationUrlsFile = "/dto/pagination_urls.dto.json"
	private val paginationStartFile = "/dto/pagination_start.dto.json"
	private val paginationMiddleFile = "/dto/pagination_middle.dto.json"
	private val paginationEndFile = "/dto/pagination_ends.dto.json"

	@Test
	fun test_PaginationUrlsDecode() {
		loadAndDeserialize(paginationUrlsFile, PaginationUrlsDto.serializer()) { sut ->
			assertNotNull(sut)
			assertNotNull(sut.first)
			assertNotNull(sut.previous)
			assertNotNull(sut.next)
			assertNotNull(sut.last)
		}
	}

	@Test
	fun test_StartingPagination() {
		loadAndDeserialize(paginationStartFile, PaginationDto.serializer()) { sut ->
			assertNotNull(sut)
			assertNull(sut.paginationUrls.first)
			assertNull(sut.paginationUrls.previous)
			assertNotNull(sut.paginationUrls.next)
			assertNotNull(sut.paginationUrls.last)

			assertEquals(1, sut.page)
			assertEquals(200, sut.pages)
			assertEquals(50, sut.pageSize)
			assertEquals(29176, sut.totalItems)
		}
	}

	@Test
	fun test_EndingPagination() {
		loadAndDeserialize(paginationEndFile, PaginationDto.serializer()) { sut ->
			assertNotNull(sut)
			assertNotNull(sut.paginationUrls.first)
			assertNotNull(sut.paginationUrls.previous)
			assertNull(sut.paginationUrls.next)
			assertNull(sut.paginationUrls.last)

			assertEquals(200, sut.pages)
			assertEquals(sut.pages, sut.page)
			assertEquals(50, sut.pageSize)
			assertEquals(29225, sut.totalItems)
		}
	}

	@Test
	fun test_MiddlePagination() {
		loadAndDeserialize(paginationMiddleFile, PaginationDto.serializer()) { sut ->
			assertNotNull(sut)
			assertNotNull(sut.paginationUrls.first)
			assertNotNull(sut.paginationUrls.previous)
			assertNotNull(sut.paginationUrls.next)
			assertNotNull(sut.paginationUrls.last)

			assertTrue( sut.paginationUrls.previous?.contains("page=${sut.page-1}") ?: false)
			assertTrue( sut.paginationUrls.next?.contains("page=${sut.page+1}") ?: false)
			assertEquals(200, sut.pages)
			assertEquals(2, sut.page)
			assertEquals(50, sut.pageSize)
			assertEquals(29225, sut.totalItems)
		}
	}
}
