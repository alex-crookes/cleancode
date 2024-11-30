package com.alexcrookes.repository.collection

import com.alexcrookes.repository.collection.fakes.FakeApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class CollectionRepositoryTests {
	private lateinit var sut: CollectionRepository

	@Before
	fun tearUp() {
		val api = FakeApi()
		sut = CollectionRepositoryImplementation(api)
	}

	@Test
	fun test_LoadFolders() = runTest {
		val folders = sut.getFolders("HiFiChild")

		assertNotNull(folders)
		val result = folders.getOrElse {
			throw AssertionError("No folders")
		}

		assertEquals(2, result.size)
	}

	@Test
	fun test_GetEntireCollection() = runTest {
		val collection = sut.getCollection("HiFiChild", 0)

		assertNotNull(collection)
		val result = collection.getOrElse {
			throw AssertionError("No folders")
		}

		assertEquals(260, result.size)
	}

}
