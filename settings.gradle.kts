pluginManagement {
	repositories {
		google {
			content {
				includeGroupByRegex("com\\.android.*")
				includeGroupByRegex("com\\.google.*")
				includeGroupByRegex("androidx.*")
			}
		}
		mavenCentral()
		gradlePluginPortal()
	}
}
dependencyResolutionManagement {
	repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
	repositories {
		google()
		mavenCentral()
	}
}

rootProject.name = "CleanCode"
include(":app")
include(":provider:fileIO")
include(":provider:local-preferences")
include(":core")
include(":provider:api")
include(":repository:catalog")
include(":domain")
include(":ui")
include(":provider:cache")
