plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlinxSerialization)
	alias(libs.plugins.hilt.android)
	alias(libs.plugins.devtools.ksp)
}

android {
	namespace = "com.alexcrookes.provider.api"
//	buildFeatures {
//		buildConfig = true
//	}
}

dependencies {
	implementation(libs.kotlin.serialization.json)
	implementation(libs.hilt)
	ksp(libs.hilt.compiler)
	implementation(project(":core"))
	implementation(project(":provider:local-preferences"))
	implementation(libs.androidx.core.ktx)
	implementation(libs.ktor.client)
	implementation(libs.ktor.client.android)
	implementation(libs.ktor.client.json)
	implementation(libs.ktor.client.negotiation)


	testImplementation(project(":provider:fileIO"))
	testImplementation(libs.junit)
	testImplementation(libs.kotlinx.coroutines.test)
	androidTestImplementation(libs.androidx.junit)
}
