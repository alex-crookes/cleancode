package com.alexcrookes.ui

import com.alexcrookes.ui.extension.humanScale
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Locale

class NumberFormattingTests {

	@Test
	fun test_SmallIntegersFormatCorrectly() {
		val sut = 2
		assertEquals("2", sut.humanScale(Locale.ENGLISH))
	}

	@Test
	fun test_ThousandsIntegersFormatCorrectly() {
		val sut = 998_000
		assertEquals("998k", sut.humanScale(Locale.ENGLISH))
	}

	@Test
	fun test_ThousandIntegersWithPlacesFormatCorrectly() {
		val sut = 998_100
		assertEquals("998.1k", sut.humanScale(Locale.ENGLISH))
	}


	@Test
	fun test_MillionsIntegersWithPlacesFormatCorrectly() {
		val sut = 1_748_100
		assertEquals("1.7m", sut.humanScale(Locale.ENGLISH))
	}

	@Test
	fun test_BillionsIntegersWithPlacesFormatCorrectly() {
		val sut = 1_748_100_000
		assertEquals("1.7b", sut.humanScale(Locale.ENGLISH))
	}

	@Test
	fun test_MillionsIntegersWithMaxInt() {
		val sut = Int.MAX_VALUE
		assertEquals("2.1b", sut.humanScale(Locale.ENGLISH))
	}





	@Test
	fun test_smallLong() {
		val sut = 2L
		assertEquals("2", sut.humanScale(Locale.ENGLISH))
	}

	@Test
	fun test_ThousandsLongFormatCorrectly() {
		val sut = 998_000L
		assertEquals("998k", sut.humanScale(Locale.ENGLISH))
	}

	@Test
	fun test_ThousandLongWithPlacesFormatCorrectly() {
		val sut = 998_100L
		assertEquals("998.1k", sut.humanScale(Locale.ENGLISH))
	}


	@Test
	fun test_MillionsLongWithPlacesFormatCorrectly() {
		val sut = 1_748_100L
		assertEquals("1.7m", sut.humanScale(Locale.ENGLISH))
	}

	@Test
	fun test_BillionsLongWithPlacesFormatCorrectly() {
		val sut = 1_748_100_000L
		assertEquals("1.7b", sut.humanScale(Locale.ENGLISH))
	}

	@Test
	fun test_MillionsLongWithMaxInt() {
		val sut = Int.MAX_VALUE.toLong()
		assertEquals("2.1b", sut.humanScale(Locale.ENGLISH))
	}

	@Test
	fun test_MillionsLongWithMaxIntPlusOne() {
		val sut: Long = Int.MAX_VALUE.toLong() +1
		assertEquals("2.1b", sut.humanScale(Locale.ENGLISH))
	}

	@Test
	fun test_MBillionsLongWith() {
		val sut: Long = 1_234_000_000L
		assertEquals("1.2b", sut.humanScale(Locale.ENGLISH))
	}

	@Test
	fun test_TrillionsLongWith() {
		val sut: Long = 1_234_000_000_000L
		assertEquals("1.2t", sut.humanScale(Locale.ENGLISH))
	}

	@Test
	fun test_BeyondTrillionsLongWith() {
		val sut: Long = 1_234_000_000_000_000L
		assertEquals("too many", sut.humanScale(Locale.ENGLISH))
	}


	@Test
	fun test_ThousandIntegersWithPlacesFormatCorrectly_German() {
		val sut = 998_100
		assertEquals("998,1k", sut.humanScale(Locale.GERMAN))
	}

	@Test
	fun test_ThousandLongWithPlacesFormatCorrectly_German() {
		val sut = 998_100L
		assertEquals("998,1k", sut.humanScale(Locale.GERMAN))
	}
}
