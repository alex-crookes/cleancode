package com.alexcrookes.provider.api.dto_tests

import com.alexcrookes.provider.api.dto.CommunityReleaseStatusDto
import com.alexcrookes.provider.api.dto.SearchResultDto
import com.alexcrookes.provider.api.dto.UserStatusDto
import com.alexcrookes.provider.api.response.SearchResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class SearchDtoTests: DeserializeTests() {

	private val userStatusDto = "/dto/user_status.dto.json"
	private val communityReleaseStatusDto = "/dto/community_release_status.dto.json"
	private val searchResultDto = "/dto/master_search_result.dto.json"

	private val searchResultsResponsePage0 = "/responses/search_page0.results.json"
	private val searchResultsResponsePage1 = "/responses/search_page1.results.json"

	@Test
	fun test_UserStatusDtoDeserialized() {
		loadAndDeserialize(userStatusDto, UserStatusDto.serializer()) { sut ->
			assertFalse(sut.owned)
			assertFalse(sut.wanted)
		}
	}

	@Test
	fun test_CommuntiyReleaseStatusDto() {
		loadAndDeserialize(communityReleaseStatusDto, CommunityReleaseStatusDto.serializer()) { sut ->
			assertEquals(41959, sut.wantedBy)
			assertEquals(89479, sut.ownedBy)
		}
	}

	@Test
	fun test_SingleSearchResultDto() {
		loadAndDeserialize(searchResultDto, SearchResultDto.serializer()) { sut ->
			assertEquals("US", sut.country)
			assertEquals(1978.toString(), sut.year)
			assertEquals(4, sut.format?.size)
			assertEquals("Stereo", sut.format?.last())
			assertEquals(15, sut.labels?.size)
			assertEquals("Oversnare Music", sut.labels?.last())
			assertEquals("master", sut.type)
			assertEquals(1, sut.genres?.size)
			assertEquals("Rock", sut.genres?.last())
			assertEquals(3, sut.styles?.size)
			assertEquals("Power Pop", sut.styles?.last())
			assertEquals(69666, sut.id)
			assertEquals(21, sut.barcodes?.size)
			assertFalse(sut.userStatus.owned)
			assertEquals(sut.id, sut.masterId)
			assertTrue(sut.masterUrl?.contains(sut.masterId.toString()) ?: false)
			assertEquals("6E-135", sut.catalogNumber)
			assertEquals("The Cars - The Cars", sut.title)
			assertNotNull(sut.thumbnailImage)
			assertNotNull(sut.coverImage)
			assertEquals(41959L, sut.communityStatus?.wantedBy)
			assertTrue(sut.resourceUrl.contains(sut.id.toString()))
			assertTrue(sut.relativeUrl.contains(sut.id.toString()))

		}
	}

	@Test
	fun test_SearchResultsResponse() {
		loadAndDeserialize(searchResultsResponsePage0, SearchResponse.serializer()) { sut ->
			assertNotNull(sut)
			assertNull(sut.pagination.paginationUrls.first)
			assertNull(sut.pagination.paginationUrls.previous)
			assertNotNull(sut.pagination.paginationUrls.next)
			assertNotNull(sut.pagination.paginationUrls.last)
		}
	}

	@Test
	fun test_SearchResultsPage1Response() {
		loadAndDeserialize(searchResultsResponsePage1, SearchResponse.serializer()) { sut ->
			assertNotNull(sut)
			assertNotNull(sut.pagination.paginationUrls.first)
			assertNotNull(sut.pagination.paginationUrls.previous)
			assertNotNull(sut.pagination.paginationUrls.next)
			assertNotNull(sut.pagination.paginationUrls.last)
		}
	}
}
