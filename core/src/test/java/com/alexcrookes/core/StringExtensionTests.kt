package com.alexcrookes.core

import com.alexcrookes.core.extension.iso8601ToEpoch
import com.alexcrookes.core.extension.sqlDateStringToEpoch
import org.junit.Assert.assertEquals
import org.junit.Test

private const val timeStamp = "2024-11-27 16:20:03"

private const val timeStampEpoch = 1732724403000
private const val oneHour = 1000 * 60 * 60;

class StringExtensionTests {
	@Test
	fun test_sqlDateToEpoch() {
		val sut = timeStamp
		val epoch = sut.sqlDateStringToEpoch()

		assertEquals(timeStampEpoch, epoch)
	}

	@Test
	fun test_invalidSqlDateToEpoch() {
		val sut = "2024-11-27 16:20"
		val epoch = sut.sqlDateStringToEpoch()

		assertEquals(0, epoch)
	}


	@Test
	fun test_ValidIso8601AtGmt() {
		val sut = "2024-11-27T16:20:03+00:00"
		val epoch = sut.iso8601ToEpoch()

		assertEquals(timeStampEpoch, epoch)
	}

	@Test
	fun test_ValidIso8601AtPlusOne() {
		val sut = "2024-11-27T16:20:03+01:00"
		val epoch = sut.iso8601ToEpoch()

		assertEquals(timeStampEpoch, epoch + oneHour)
	}

	@Test
	fun test_ValidIso8601AtMinusFive() {
		val sut = "2024-11-27T16:20:03-05:00"
		val epoch = sut.iso8601ToEpoch()

		assertEquals(timeStampEpoch, epoch - (5 * oneHour))
	}
}
