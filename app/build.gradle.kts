plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlin.compose)
	alias(libs.plugins.hilt.android)
	alias(libs.plugins.devtools.ksp)
}

android {
	namespace = "com.alexcrookes.cleancode"

	buildFeatures {
		compose = true
		buildConfig = true
	}
}

dependencies {
	implementation(libs.hilt)
	implementation(project(":domain"))
	implementation(project(":ui"))
	implementation(project(":core"))
	implementation(libs.androidx.media.player)
	implementation(libs.androidx.media.ui)
	implementation(libs.glide.compose)
	ksp(libs.hilt.compiler)
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
	implementation(libs.hilt.navigation.compose)

	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
}
