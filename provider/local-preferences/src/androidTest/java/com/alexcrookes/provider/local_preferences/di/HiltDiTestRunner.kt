package com.alexcrookes.provider.local_preferences.di

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

class HiltDiTestRunner : AndroidJUnitRunner() {
	override fun newApplication(
		cl: ClassLoader?,
		className: String?,
		context: Context?,
	): Application {
		return super.newApplication(
			cl,
			HiltTestApplication::class.java.name,
			context
		)
	}
}
