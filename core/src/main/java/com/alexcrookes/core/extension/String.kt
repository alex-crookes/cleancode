package com.alexcrookes.core.extension

import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.util.Locale
import java.util.TimeZone

const val SqlFormat = "yyyy-MM-dd HH:mm:ss"

const val Iso8601Format = "yyyy-MM-dd'T'HH:mm:ssXXX"//"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

fun String.sqlDateStringToEpoch(): Epoch {
	//val offsetTime = OffsetDateTime.parse(this)
	try {
		val sdf = SimpleDateFormat(SqlFormat, Locale.getDefault())
		sdf.timeZone = TimeZone.getTimeZone("GMT")
		val localDate = sdf.parse(this)
		return localDate?.time ?: 0
	} catch (e: Exception) {
		return 0L
	}
}

fun String.iso8601ToEpoch(): Epoch {
	try {
		val sdf = SimpleDateFormat(Iso8601Format, Locale.getDefault())
		sdf.timeZone = TimeZone.getDefault()
		val localDate = sdf.parse(this)
		return localDate?.time ?: 0
	} catch (e: Exception) {
		return -1L
	}
}
