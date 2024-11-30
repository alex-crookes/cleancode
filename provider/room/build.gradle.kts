plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlinxSerialization)
	alias(libs.plugins.hilt.android)
	alias(libs.plugins.devtools.ksp)
	alias(libs.plugins.room)
}

android {
	namespace = "com.alexcrookes.provider.room"
}

room {
	schemaDirectory("$projectDir/schemas")
}

dependencies {
	implementation(libs.androidx.room)
	implementation(libs.androidx.room.ktx)
	implementation(libs.hilt)
	implementation(project(":core"))
	ksp(libs.hilt.compiler)
	ksp(libs.androidx.room.ksp.compiler)
	implementation(libs.androidx.core.ktx)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)


}
