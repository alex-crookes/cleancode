plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.devtools.ksp)
	alias(libs.plugins.hilt.android)
	alias(libs.plugins.kotlinxSerialization)
}

android {
	namespace = "com.alexcrookes.core"
}

dependencies {
	ksp(libs.hilt.compiler)
	implementation(libs.androidx.core.ktx)
	implementation(libs.kotlin.serialization.json)
	implementation(libs.hilt)
	implementation(libs.androidx.core.ktx)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
}
