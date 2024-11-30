package com.alexcrookes.core.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

typealias Epoch = Long

val Epoch.asShortSqlDateString: String
	get() {
		val date = Date(this)
		val dateFormat = SimpleDateFormat("yyyy-MM-dd")
		dateFormat.timeZone = TimeZone.getDefault()
		return dateFormat.format(date)
	}
