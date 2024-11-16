package com.alexcrookes.domain.catalog

sealed class CatalogIntent {
	data class GetRelease(val releaseId: Long): CatalogIntent()
}
