plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.hilt.android)
	alias(libs.plugins.devtools.ksp)
}

android {
	namespace = "com.alexcrookes.domain.catalog"
}

dependencies {
	implementation(project(":core"))
	implementation(project(":repository:catalog"))
	implementation(libs.androidx.core.ktx)
	ksp(libs.hilt.compiler)
	implementation(libs.hilt)

	testImplementation(libs.junit)
	testImplementation(libs.kotlinx.coroutines.test)
	androidTestImplementation(libs.androidx.junit)
}
