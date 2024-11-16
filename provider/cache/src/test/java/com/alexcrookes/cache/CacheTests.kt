package com.alexcrookes.cache

import com.alexcrookes.cache.fake.FakePreferences
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class CacheTests {

	lateinit var sut: Cache
	lateinit var prefs: FakePreferences

	@Before
	fun tearUp() {
		prefs = FakePreferences()
		sut = CacheImplementation(prefs)
	}

	@Test
	fun test_WriteBasicObject() {
		val data = TestData()

		runTest {
			assertEquals(0, prefs.countOf())
			sut.storeUntil("test_key", 10_000, data, TestData.serializer())
			assertEquals(1, prefs.countOf())
		}
	}

	@Test
	fun test_ReadBackBasicObject() {
		runTest {
			test_WriteBasicObject()

			val retrieved = sut.retrieve("test_key", TestData.serializer())
			assertNotNull(retrieved)
			assertEquals("Alan", retrieved?.name)
			assertEquals(3, retrieved?.innerData?.stringList?.size)
		}
	}

	@Test
	fun test_WriteAndExpire() {
		val key = "expires_test_key"
		val data = TestData()

		runTest {
			sut.storeUntil(key, 10, data, TestData.serializer())
			//delay(20)
			Thread.sleep(20L)

			val retrieved = sut.retrieve(key, TestData.serializer())
			assertNull(retrieved)
			assertEquals(0, prefs.countOf())
		}
	}

	@Test
	fun test_ClearKey() {
		runTest {
			test_WriteBasicObject()
			sut.clear("test_key")
			assertEquals(0, prefs.countOf())
		}
	}
}

@Serializable
private data class TestData(
	@SerialName("number") val number: Int = 1,
	@SerialName("name") val name: String = "Alan",
	@SerialName("innerThing") val innerData: InnerTestData = InnerTestData()
)


@Serializable
private data class InnerTestData(
	@SerialName("long") val longNumber: Long = 10_000L,
	@SerialName("listOf") val stringList: List<String> = listOf("one", "two", "three")
)
