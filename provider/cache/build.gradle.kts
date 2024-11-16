plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.hilt.android)
	alias(libs.plugins.devtools.ksp)
	alias(libs.plugins.kotlinxSerialization)
}

android {
	namespace = "com.alexcrookes.cache"
}

dependencies {
	ksp(libs.hilt.compiler)
	implementation(libs.hilt)
	implementation(project(":core"))
	implementation(project(":provider:local-preferences"))
	implementation(libs.androidx.core.ktx)
	implementation(libs.kotlin.serialization.json)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	testImplementation(libs.kotlinx.coroutines.test)
}
