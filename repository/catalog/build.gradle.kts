plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlinxSerialization)
	alias(libs.plugins.hilt.android)
	alias(libs.plugins.devtools.ksp)
}

android {
	namespace = "com.alexcrookes.repository.catalog"
}

dependencies {
	implementation(project(":core"))
	implementation(project(":provider:local-preferences"))
	implementation(project(":provider:api"))
	implementation(project(":provider:cache"))
	implementation(libs.androidx.core.ktx)
	implementation(libs.hilt)
	implementation(libs.androidx.lifecycle.viewmodel.compose)
	ksp(libs.hilt.compiler)

	testImplementation(project(":provider:cache"))
	testImplementation(project(":provider:local-preferences"))
	testImplementation(project(":provider:fileIO"))
	testImplementation(libs.junit)
	testImplementation(libs.kotlinx.coroutines.test)
	androidTestImplementation(libs.androidx.junit)
	implementation(libs.kotlin.serialization.json)
}
