plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlinxSerialization)
	alias(libs.plugins.hilt.android)
	alias(libs.plugins.devtools.ksp)
}

android {
	namespace = "com.alexcrookes.provider.local_preferences"

	defaultConfig {
		testInstrumentationRunner =
			"com.alexcrookes.provider.local_preferences.di.HiltDiTestRunner"
	}
}

dependencies {
	implementation(project(":core"))
	implementation(libs.hilt)
	implementation(libs.androidx.core.ktx)
	ksp(libs.hilt.compiler)
	implementation(libs.kotlin.serialization.json)
	implementation(libs.androidx.datastore.preferences)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.hilt.android.testing)
	androidTestImplementation(libs.androidx.core.testing)
	androidTestImplementation(libs.androidx.test.runner)
	androidTestImplementation(libs.kotlinx.coroutines.test)
}
