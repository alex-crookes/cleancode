package com.alexcrookes.provider.api

import com.alexcrookes.provider.api.fakes.FakePreferences
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

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
		sut.authenticate("")
		val result = sut.getReleaseDetails(249504)
		assertNotNull(result)
		val data = result.getOrNull()
		assertNotNull(data)
		data?.let {
			assertEquals(249504, data.id)
			assertEquals("Never Gonna Give You Up", data.title)
			assertEquals(4, data.images.size)
		} ?: run {
			AssertionError("No Data")
		}
	}
}
