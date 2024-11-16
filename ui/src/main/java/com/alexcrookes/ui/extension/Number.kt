package com.alexcrookes.ui.extension

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

fun Long.humanScale(locale: Locale = Locale.ENGLISH): String {
		return when (val value = this) {
			in 0..Int.MAX_VALUE ->
				value.toInt().humanScale(locale)

			in Int.MAX_VALUE ..<1_000_000_000_000 -> {
				val rounded = value / 1_000_000_000.0
				return print(locale, rounded, "b")
 			}


			in 1_000_000_000_000 ..<1_000_000_000_000_000 -> {
				val rounded = value / 1_000_000_000_000.0
				return print(locale, rounded, "t")
			}

			else -> "too many"
		}
	}

fun Int.humanScale(locale: Locale = Locale.ENGLISH): String {
	return when (val value = this) {
		in 0..<1000 ->
			String.format(locale, "%d", value)

		in 1000..<1_000_000 -> {
			val rounded: Double = value / 1000.0
			return print(locale, rounded, "k")
		}

		in 1_000_000..<1_000_000_000 -> {
			val rounded: Double = value / 1_000_000.0
			return print(locale, rounded, "m")
		}

		in 1_000_000_000..Int.MAX_VALUE -> {
			val rounded: Double = value / 1_000_000_000.0
			return print(locale, rounded, "b")
		}

		else ->
			"-1"
	}
}

private fun print(locale: Locale, rounded: Double, size: String): String {
	val formatter = DecimalFormat("#.#", DecimalFormatSymbols(locale))
	formatter.roundingMode = RoundingMode.FLOOR
	return String.format(locale, "%s%s", formatter.format(rounded), size)
}
