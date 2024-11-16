package com.alexcrookes.repository.catalog

import com.alexcrookes.cache.CacheImplementation
import com.alexcrookes.core.extension.primaryImage
import com.alexcrookes.repository.catalog.fakes.FakeApi
import com.alexcrookes.repository.catalog.fakes.FakePreferences
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Assert.assertNotNull

class RepositoryTest {

	@Test
	fun test_LoadRelease() {
		val repo = CatalogRepositoryImplementation(
			api = FakeApi(),
			cache = CacheImplementation(FakePreferences()),
			preferences = FakePreferences()
		)

		runTest {
			val doc = repo.getReleaseDetails(249504)
			assertNotNull(doc)
			val sut = doc.getOrNull() ?: throw AssertionError("NO data")

			assertEquals("${sut.images.size}", 4, sut.images.size)
			assertNotNull(sut.primaryImage)
			assertNotNull("${sut.primaryImage?.display}", sut.primaryImage?.display)
		}

	}
}
