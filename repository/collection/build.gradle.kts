plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlinxSerialization)
	alias(libs.plugins.hilt.android)
	alias(libs.plugins.devtools.ksp)
}

android {
	namespace = "com.alexcrookes.repository.collection"
}

dependencies {

	implementation(libs.androidx.core.ktx)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	implementation(project(":core"))
	implementation(project(":provider:local-preferences"))
	implementation(project(":provider:api"))
	implementation(project(":provider:room"))
	implementation(libs.androidx.core.ktx)
	implementation(libs.hilt)
	implementation(libs.androidx.lifecycle.viewmodel.compose)
	ksp(libs.hilt.compiler)

	testImplementation(project(":provider:local-preferences"))
	testImplementation(project(":provider:fileIO"))
	testImplementation(libs.junit)
	testImplementation(libs.kotlinx.coroutines.test)
	androidTestImplementation(libs.androidx.junit)
	implementation(libs.kotlin.serialization.json)
}
