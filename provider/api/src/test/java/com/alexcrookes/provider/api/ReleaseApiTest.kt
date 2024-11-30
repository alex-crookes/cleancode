package com.alexcrookes.provider.api

import com.alexcrookes.provider.api.fakes.FakePreferences
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

private const val userToken = "RrUEkpDRsolZbtIPXVESHVLqhBqnvyKYmnBZmHMd"

class ReleaseApiTest {
	private lateinit var sut: Api

	@Before
	fun tearUp() {
		val prefs = FakePreferences()
		sut = ApiImplementation(
			prefs = prefs,
			consumerKey = "IwIgCnDbGWDeErjDTdND",
			consumerSecret = "KNGsTnCuLxszBJvVcxpcBXIYaIKCHBdt"
		)
	}

	@Test
	fun test_GetMasterRelease() = runTest {
		// this is a little hacky. This api technically does not require auth
		// but we need it to build the HttpClient
		sut.authenticate(userToken)
		val result = sut.getReleaseDetails(249504)
		assertNotNull(result)
		val data = result.getOrElse {
			throw AssertionError("No Results - ${it.message}")
		}

		assertEquals(249504, data.id)
		assertEquals("Never Gonna Give You Up", data.title)
		assertEquals(4, data.images.size)

	}

	@Test
	fun test_getCollectionFolders() = runTest {
		sut.authenticate(userToken)
		val result = sut.getCollectionFolders("HiFiChild")
		assertNotNull(result)
		val data = result.getOrElse {
			throw AssertionError("No Results - ${it.message}")
		}

		assertEquals(2, data.folders.size)
	}

	@Test
	fun test_getCollectionResults() = runTest {
		sut.authenticate(userToken)
		val result = sut.getCollectionReleasesForFolder(0,"HiFiChild")
		assertNotNull(result)
		val data = result.getOrElse {
			throw AssertionError("No Results - ${it.message}")
		}

		assertEquals(50, data.releases.size)

	}

	@Test
 	fun test_getCollectionResults_Page2() = runTest {
		sut.authenticate(userToken)
		val result = sut.getCollectionReleasesForFolderWithPage(
			folderId = 0, userName = "HiFiChild", page = 2
		)
		assertNotNull(result)

		val data = result.getOrElse {
			throw AssertionError("No Results - ${it.message}")
		}

		assertEquals(50, data.releases.size)
	}

	@Test
	fun test_GetEntireCollection() = runTest {
		sut.authenticate(userToken)

		val start = System.currentTimeMillis()
		val result = sut.getAllCollectionReleasesForFolder(0, "HiFiChild")
		val end = System.currentTimeMillis()
		assertNotNull(result)

		val data = result.getOrElse {
			throw AssertionError("No Results - ${it.message}")
		}

		assertEquals(260, data.size)
		val duration = (end - start) / 1000L
	}
}
