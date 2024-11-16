package com.alexcrookes.repository.catalog

import com.alexcrookes.core.entities.Release

interface CatalogRepository {
	suspend fun getReleaseDetails(releaseId: Long): Result<Release>
}
