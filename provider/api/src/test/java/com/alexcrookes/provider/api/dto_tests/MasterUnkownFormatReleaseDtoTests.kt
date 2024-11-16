package com.alexcrookes.provider.api.dto_tests

import com.alexcrookes.provider.api.dto.CommunityContributorDto
import com.alexcrookes.provider.api.dto.CommunityRatingDto
import com.alexcrookes.provider.api.dto.EntityDto
import com.alexcrookes.provider.api.dto.MasterReleaseDto
import com.alexcrookes.provider.api.dto.ReleaseCommunityMetaDto
import com.alexcrookes.provider.api.dto.ReleaseFormatDto
import com.alexcrookes.provider.api.dto.ReleaseIdentifierDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class MasterUnkownFormatReleaseDtoTests : DeserializeTests() {
	private val labelEntityDto = "/dto/label_entity.dto.json"
	private val recordCompanyEntityDto = "/dto/record_company_entity.dto.json"
	private val entityCollectionDto = "/dto/entity_collection.dto.json"
	private val releaseFormatDto = "/dto/release_format.dto.json"
	private val communityContributorDto = "/dto/community_contributor.dto.json"
	private val communityRatingDto = "/dto/community_rating.dto.json"
	private val communityMetaDto = "/dto/release_community_meta.dto.json"
	private val releaseIdentifier = "/dto/release_identifier.dto.json"
	private val masterRelease = "/dto/master_release.dto.json"
//    private val masterReleaseFormatQuantityFailing =
//        "/dto/master_release_format_quantity_issue.dto.json"

	@Serializable
	private data class EntityTestList(
		@SerialName("items") val items: List<EntityDto>
	)

	@Test
	fun test_LabelEntityDtoDeserialized() {
		loadAndDeserialize(labelEntityDto, EntityDto.serializer()) { sut ->
			assertEquals(1.toString(), sut.type)
			assertEquals("Label", sut.typeName)
			assertEquals("Elektra", sut.name)
			assertEquals("6E-135", sut.catalogueNumber)
			assertEquals(651, sut.id)
			assertTrue(sut.resourceUrl.contains(sut.id.toString()))
		}
	}

	@Test
	fun test_RecordCompanyEntityDtoDeserialized() {
		loadAndDeserialize(recordCompanyEntityDto, EntityDto.serializer()) { sut ->
			assertEquals(4.toString(), sut.type)
			assertEquals("Record Company", sut.typeName)
			assertEquals("Warner Communications Inc.", sut.name)
			assertEquals("", sut.catalogueNumber)
			assertEquals(141847, sut.id)
			assertTrue(sut.resourceUrl.contains(sut.id.toString()))
		}
	}

	@Test
	fun test_EntityCollectionDtoDeserialized() {
		loadAndDeserialize(entityCollectionDto, EntityTestList.serializer()) { sut ->
			assertEquals(14, sut.items.size)
			assertEquals(14.toString(), sut.items.last().type)
			assertEquals("Copyright (c)", sut.items.last().typeName)
		}
	}

	@Test
	fun test_FormatDtoDeserialized() {
		loadAndDeserialize(releaseFormatDto, ReleaseFormatDto.serializer()) { sut ->
			assertEquals("Vinyl", sut.name)
			assertEquals(1.toString(), sut.quantity)
			assertEquals(3, sut.descriptions.size)
		}
	}

	@Test
	fun test_CommunityContributorDtoDeserialized() {
		loadAndDeserialize(communityContributorDto, CommunityContributorDto.serializer()) { sut ->
			assertEquals("gregclow", sut.userName)
			assertTrue(sut.userName.contains(sut.userName))
			assertTrue(sut.resourceUrl.contains(sut.userName))
		}
	}

	@Test
	fun test_CommunityRatingDtoDeserialized() {
		loadAndDeserialize(communityRatingDto, CommunityRatingDto.serializer()) { sut ->
			assertEquals(1982, sut.ratingCount)
			assertEquals(4.27, sut.averageRating, 0.0)
		}
	}

	@Test
	fun test_CommunityDtoDeserialized() {
		loadAndDeserialize(communityMetaDto, ReleaseCommunityMetaDto.serializer()) { sut ->
			assertEquals(30390, sut.have)
			assertEquals(4395, sut.want)
			assertEquals(1982, sut.rating.ratingCount)
			assertEquals(4.27, sut.rating.averageRating, 0.0)
			assertEquals("gregclow", sut.submitter.userName)
			assertEquals(1, sut.contributors.size)
			assertEquals("Complete and Correct", sut.dataQuality)
			assertEquals("Accepted", sut.status)
		}
	}

	@Test
	fun test_ReleaseIdentifierDtoDeserialized() {
		loadAndDeserialize(releaseIdentifier, ReleaseIdentifierDto.serializer()) { sut ->
			assertEquals("Pressing Plant ID", sut.type)
			assertEquals("PRC-W", sut.value)
			assertEquals("Labels", sut.description)
		}
	}

	@Test
	fun test_MasterReleaseDtoDeserialized() {
		loadAndDeserialize(masterRelease, MasterReleaseDto.serializer()) { sut ->
			assertEquals(190219, sut.id)
			assertTrue(sut.resourceUrl.contains(sut.id.toString()))
			assertTrue(sut.permalink.contains(sut.id.toString()))

			assertEquals(1978, sut.year)
			assertEquals("Accepted", sut.status)
			assertEquals(1, sut.artists.size)
			assertEquals("Cars, The", sut.artistsSortName)
			assertEquals(1, sut.labels.size)
			assertEquals(651, sut.labels.first().id)
			assertEquals(0, sut.series.size)
			assertEquals(14, sut.companies.size)
			assertEquals(1, sut.formats.size)
			assertEquals("Complete and Correct", sut.dataQuality)
			assertEquals(30390, sut.community.have)
			assertEquals(1982, sut.community.rating.ratingCount)
			assertEquals("gregclow", sut.community.submitter.userName)
			assertEquals(47, sut.community.contributors.size)
			assertEquals(1, sut.formatQuantity)
			assertTrue(sut.dateAdded.contains("2003"))
			assertTrue(sut.dateChanged.contains("2023"))
			assertEquals(47, sut.numberForSale)
			assertEquals(5.0, sut.lowestPrice, 0.0)
			assertEquals(69666, sut.masterId)
			assertTrue(sut.masterUrl.contains(sut.masterId.toString()))
			assertEquals("06 Jun 1978", sut.releasedFormatted)
			assertEquals("The Cars", sut.title)
			assertEquals("US", sut.country)
			assertEquals("1978-06-06", sut.released)
			assertNotNull(sut.notes)

			assertEquals(21, sut.identifiers.size)
			assertEquals(9, sut.videos.size)
			assertEquals(1, sut.genres.size)
			assertEquals(3, sut.styles.size)
			assertEquals(9, sut.trackList.size)
			assertEquals(15, sut.extraArtists.size)
			assertEquals(8, sut.images.size)
			assertEquals(230, sut.estimatedWeight)
			assertFalse(sut.blockedFromSale)
			assertEquals("", sut.thumbnail)
		}
	}


//    @Test
//    fun test_MasterReleaseDtoFormatQuantityIssuesDeserialized() {
//        loadAndDeserialize(masterReleaseFormatQuantityFailing, MasterReleaseDto.serializer()) { sut ->
//            assertEquals(190219, sut.id)
//            assertTrue(sut.resourceUrl.contains(sut.id.toString()))
//            assertTrue(sut.permalink.contains(sut.id.toString()))
//
//            assertEquals(1978, sut.year)
//            assertEquals("Accepted", sut.status)
//            assertEquals(1, sut.artists.size)
//            assertEquals("Cars, The", sut.artistsSortName)
//            assertEquals(1, sut.labels.size)
//            assertEquals(651, sut.labels.first().id)
//            assertEquals(0, sut.series.size)
//            assertEquals(14, sut.companies.size)
//            assertEquals(1, sut.formats.size)
//            assertEquals("Complete and Correct", sut.dataQuality)
//            assertEquals(30390, sut.community.have)
//            assertEquals(1982, sut.community.rating.ratingCount)
//            assertEquals("gregclow", sut.community.submitter.userName)
//            assertEquals(47, sut.community.contributors.size)
//            assertEquals(1, sut.formatQuantity)
//            assertTrue(sut.dateAdded.contains("2003"))
//            assertTrue(sut.dateChanged.contains("2023"))
//            assertEquals(47, sut.numberForSale)
//            assertEquals(5.0, sut.lowestPrice, 0.0)
//            assertEquals(69666, sut.masterId)
//            assertTrue(sut.masterUrl.contains(sut.masterId.toString()))
//            assertEquals("06 Jun 1978", sut.releasedFormatted)
//            assertEquals("The Cars", sut.title)
//            assertEquals("US", sut.country)
//            assertEquals("1978-06-06", sut.released)
//            assertNotNull(sut.notes)
//
//            assertEquals(21, sut.identifiers.size)
//            assertEquals(9, sut.videos.size)
//            assertEquals(1, sut.genres.size)
//            assertEquals(3, sut.styles.size)
//            assertEquals(9, sut.trackList.size)
//            assertEquals(15, sut.extraArtists.size)
//            assertEquals(8, sut.images.size)
//            assertEquals(230, sut.estimatedWeight)
//            assertFalse(sut.blockedFromSale)
//            assertEquals("", sut.thumbnail)
//        }
//    }
}
