plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlin.compose)
	alias(libs.plugins.hilt.android)
	alias(libs.plugins.devtools.ksp)
}

android {
	namespace = "com.alexcrookes.ui"
}

dependencies {

	ksp(libs.hilt.compiler)
	implementation(libs.hilt)
	implementation(project(":core"))
	implementation(libs.androidx.core.ktx)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
	implementation(libs.glide.compose)
	implementation(libs.androidx.activity.compose)
	implementation(libs.androidx.media.player)
	implementation(libs.androidx.media.player.dash)
	implementation(libs.androidx.media.player.hls)
	implementation(libs.androidx.media.player.ima)
	implementation(libs.androidx.media.player.rtsp)
	implementation(libs.androidx.media.player.smooth)
	implementation(libs.androidx.media.session)
	implementation(libs.androidx.media.ui)
	implementation(libs.youtube.player)

	implementation(libs.hilt.navigation.compose)

	debugImplementation(libs.ui.tooling)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}
