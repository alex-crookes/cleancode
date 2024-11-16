plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlinxSerialization)
	alias(libs.plugins.hilt.android)
	alias(libs.plugins.devtools.ksp)
}

android {
	namespace = "com.alexcrookes.provider.fileio"
}

dependencies {
	implementation(libs.hilt)
	implementation(libs.androidx.core.ktx)
	implementation(libs.kotlin.serialization.json)
	ksp(libs.hilt.compiler)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
}
