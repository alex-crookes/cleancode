import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
import java.util.Properties

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
	alias(libs.plugins.android.application) apply false
	alias(libs.plugins.kotlin.android) apply false
	alias(libs.plugins.kotlin.compose) apply false
	alias(libs.plugins.android.library) apply false
	alias(libs.plugins.kotlinxSerialization) apply false
	alias(libs.plugins.hilt.android) apply false
	alias(libs.plugins.devtools.ksp) apply false
	kotlin("jvm") version libs.versions.kotlin
}

// See - https://medium.com/@andretmarques/beginners-guide-to-android-modularisation-streamlining-shared-configurations-settings-with-kotlin-ed3fc065e174
// Apply configurations to all sub-projects within the main project
subprojects {
// The 'afterEvaluate' block ensures configurations are applied after the project has been evaluated.
	afterEvaluate {

		// Check if the current project is extension-aware, to make sure we're dealing with an Android project.
		(this as? ExtensionAware)?.extensions?.run {
			// Specific configurations for the App module, helping to keep the app build script clean.
			findByType<BaseAppModuleExtension>()?.run {

				// Default configurations that apply to all build variants unless specifically overridden.
				defaultConfig {
					applicationId = "com.alexcrookes.cleancode" // The unique identifier for the app
					versionCode = 1             // Version code, incremented for each release.
					versionName = "1.0.0"         // User-visible version name.

					val keystoreFile = project.rootProject.file("apikeys.properties")
					val properties = Properties()
					properties.load(keystoreFile.inputStream())

					val discogsSecret = properties.getProperty("DISCOGS_CONSUMER_SECRET") ?: ""
					buildConfigField(
						type = "String",
						name = "DISCOGS_CONSUMER_SECRET",
						value = discogsSecret
					)
					val discogsKey = properties.getProperty("DISCOGS_CONSUMER_KEY") ?: ""
					buildConfigField(
						type = "String",
						name = "DISCOGS_CONSUMER_KEY",
						value = discogsKey
					)
				}


				// Defines how different build types (debug/release) should be set up.
				buildTypes {
					debug {
						isDefault = true       // Makes 'debug' the default build type.
						isDebuggable = true    // Enables debugging for this build type.
						applicationIdSuffix =
							".debug" // Adds a suffix to the applicationId for the debug build.
					}
					release {
						isMinifyEnabled = true    // Enables code minification.
						isShrinkResources = true  // Reduces unused resources.
						isDebuggable = false      // Disables debugging for release builds.
						proguardFiles(
							getDefaultProguardFile("proguard-android-optimize.txt"),
							"proguard-rules.pro"
						)
					}
				}

			}

			// Additional check to ensure we're configuring an Android project.
			findByType<BaseExtension>()?.run {
				// Sets the Android SDK version to be used for compiling the project.
				setCompileSdkVersion(libs.versions.sdk.compile.get().toInt())


				// Sets the minimum and target SDK versions for the app.
				defaultConfig {
					// Minimum Android version the app can run on.
					minSdk = libs.versions.sdk.min.get().toInt()
					// Android version the app is targeted towards.
					targetSdk = libs.versions.sdk.target.get().toInt()

					consumerProguardFiles("consumer-rules.pro")
				}


				// Sets compatibility for the compiled Java code.
				compileOptions {
					sourceCompatibility = JavaVersion.VERSION_17 // Source code is written using Java 21.
					targetCompatibility = JavaVersion.VERSION_17 // Compiled bytecode is compatible with Java 21.
				}
			}
		}
	}

	//tasks.named<KotlinJvmCompile>("compileKotlin") {
	tasks.withType<KotlinJvmCompile>()
		.configureEach {
			compilerOptions { jvmTarget.set(JvmTarget.JVM_17) }
		}

}
