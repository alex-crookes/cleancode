package com.alexcrookes.provider.local_preferences

import androidx.test.runner.AndroidJUnit4
import com.alexcrookes.core.coreJsonSettings
import com.alexcrookes.provider.local_preferences.model.InnerClass
import com.alexcrookes.provider.local_preferences.model.TestModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

val testModel = TestModel(
	1,
	"string",
	true,
	1.0,
	1.0f,
	InnerClass(
		2,
		"string2",
		false,
		2.0,
		2.0f,
	)
)

val testJsonString = """
{"number":1,"string":"string","boolean":true,"double":1.0,"float":1.0,"innerClass":{"number":2,"string":"string2","boolean":false,"double":2.0,"float":2.0}}
""".trimIndent()
val json = coreJsonSettings

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class ObjectSerializationTests {
	private val testDispatcher = StandardTestDispatcher()

//	private val context = ApplicationProvider.getApplicationContext<Context>()
//	private var cut2 = LocalPreferencesImplementation(context)

	@get:Rule(order = 0)
	var hiltRule = HiltAndroidRule(this)

	@Inject
	lateinit var cut: Preferences


	@Before
	fun setup() {
		hiltRule.inject()
		Dispatchers.setMain(testDispatcher)
	}

	@After
	fun tearDown() {
		Dispatchers.resetMain()
	}

	@Test
	fun test_Serializes() {
		val sut = json.encodeToString(
			TestModel.serializer(),
			testModel
		)
		assertEquals(testJsonString, sut)
	}

	@Test
	fun test_Deserializes() {
		val sut = json.decodeFromString(
			TestModel.serializer(),
			testJsonString
		)
		assertEquals(testModel, sut)
	}

	@Test
	fun writeSerialization() {

		//val sut = json.encodeToString(TestModel.serializer(), testModel)
		//assertEquals(testJsonString, sut)

		runTest {
			cut.writeWithSerialization(
				TestModel.serializer(),
				"test_testSerialization",
				testModel
			)

			val sut = cut.readFromSerialization(
				TestModel.serializer(),
				"test_testSerialization"
			)

			assertEquals(testModel, sut)
		}


	}
}
