package com.alexcrookes.domain.catalog

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

private data class One(val number: Int)
private data class Two(val number: Int, val name: String)

private val one = One(1)
private val two = Two(2, "Stephen")

class ViewModelResultTests {

	@Test
	fun test_Success() {
		var sut: ViewModelResult<One> = ViewModelResult.Loading

		assertTrue(sut is ViewModelResult.Loading)

		sut = ViewModelResult.Error( Exception("thing") )
		assertTrue(sut is ViewModelResult.Error)
		val message = (sut as ViewModelResult.Error).exception.message

		assertEquals("thing", message)
		sut = ViewModelResult.Ready(one)

		assertTrue(sut is ViewModelResult.Ready)
		assertEquals(1, (sut as? ViewModelResult.Ready)?.data?.number)
	}

	@Test
	fun test_SuccessWithTwo() {
		var sut: ViewModelResult<Two> = ViewModelResult.Loading

		assertTrue(sut is ViewModelResult.Loading)

		sut = ViewModelResult.Error( Exception("thing") )
		assertTrue(sut is ViewModelResult.Error)
		val message = (sut as ViewModelResult.Error).exception.message

		assertEquals("thing", message)
		sut = ViewModelResult.Ready(two)

		assertTrue(sut is ViewModelResult.Ready)
		assertEquals(2, (sut as? ViewModelResult.Ready)?.data?.number)
		assertEquals("Stephen", (sut as? ViewModelResult.Ready)?.data?.name)
	}
}
