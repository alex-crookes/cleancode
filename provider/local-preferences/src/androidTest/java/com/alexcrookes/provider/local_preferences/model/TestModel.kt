package com.alexcrookes.provider.local_preferences.model
/*
 * Created by alex on 2024-10-04, 10:55 a.m.
 * Copyright (c) 2024 AlchemistOne. All Rights Reserved
 * Last Modified by alex 2024-10-04, 10:55 a.m.
 *
 */

import kotlinx.serialization.Serializable

@Serializable
data class TestModel(
	val number: Int,
	val string: String,
	val boolean: Boolean,
	val double: Double,
	val float: Float,
	val innerClass: InnerClass,
)

@Serializable
data class InnerClass(
	val number: Int,
	val string: String,
	val boolean: Boolean,
	val double: Double,
	val float: Float,
)
