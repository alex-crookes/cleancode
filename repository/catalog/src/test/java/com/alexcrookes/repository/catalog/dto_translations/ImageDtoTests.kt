package com.alexcrookes.repository.catalog.dto_translations

import com.alexcrookes.core.entities.RemoteImage
import com.alexcrookes.provider.api.dto.ImageDto
import com.alexcrookes.repository.catalog.extension.asRemoteImage
import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 *
 */
class ImageDtoTests {
	@Test
	fun convert_DtoPrimaryImageToImage() {
		val dto = ImageDto(
			type = "primary",
			uri = "https://image.com/image",
			resourceUrl = "https://resources.com/image",
			uri150 = "https://url150.com/image",
			width = 150,
			height = 150,
		)

		val image = dto.asRemoteImage

		val sut: RemoteImage = image ?: throw
			AssertionError("Conversion fails")

		assertTrue(sut is RemoteImage.Primary)
	}

	@Test
	fun convert_DtoPrimaryImageWithPoorGrammarToImage() {
		val dto = ImageDto(
			type = "PrImaRY",
			uri = "https://image.com/image",
			resourceUrl = "https://resources.com/image",
			uri150 = "https://url150.com/image",
			width = 150,
			height = 150,
		)

		val image = dto.asRemoteImage

		val sut: RemoteImage = image ?: throw
		AssertionError("Conversion fails")

		assertTrue(sut is RemoteImage.Primary)
	}

	@Test
	fun convert_DtoSecondaryImageToImage() {
		val dto = ImageDto(
			type = "secondary",
			uri = "https://image.com/image",
			resourceUrl = "https://resources.com/image",
			uri150 = "https://url150.com/image",
			width = 150,
			height = 150,
		)

		val image = dto.asRemoteImage

		val sut: RemoteImage = image ?: throw
		AssertionError("Conversion fails")

		assertTrue(sut is RemoteImage.Secondary)
	}

	@Test
	fun convert_DtoSecondaryImageWithPoorGrammarToImage() {
		val dto = ImageDto(
			type = "sEcOnDaRy",
			uri = "https://image.com/image",
			resourceUrl = "https://resources.com/image",
			uri150 = "https://url150.com/image",
			width = 150,
			height = 150,
		)

		val image = dto.asRemoteImage

		val sut: RemoteImage = image ?: throw
		AssertionError("Conversion fails")

		assertTrue(sut is RemoteImage.Secondary)
	}

	@Test
	fun convert_Nulls_TertiaryImage() {
		val dto = ImageDto(
			type = "tertiary",
			uri = "https://image.com/image",
			resourceUrl = "https://resources.com/image",
			uri150 = "https://url150.com/image",
			width = 150,
			height = 150,
		)
		val sut = dto.asRemoteImage
		assertNull(sut)
	}

	@Test
	fun convert_Nulls_NoUrl() {
		val dto = ImageDto(
			type = "primary",
			uri = "",
			resourceUrl = "https://resources.com/image",
			uri150 = "https://url150.com/image",
			width = 150,
			height = 150,
		)
		val sut = dto.asRemoteImage
		assertNull(sut)
	}
}
