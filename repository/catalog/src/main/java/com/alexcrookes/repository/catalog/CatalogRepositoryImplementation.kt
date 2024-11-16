package com.alexcrookes.repository.catalog

import android.util.Log
import com.alexcrookes.cache.Cache
import com.alexcrookes.core.entities.Release
import com.alexcrookes.provider.api.Api
import com.alexcrookes.provider.api.dto.ReleaseDto
import com.alexcrookes.provider.local_preferences.Preferences
import com.alexcrookes.repository.catalog.extension.asRelease
import javax.inject.Inject

class CatalogRepositoryImplementation @Inject constructor(
	private val api: Api,
	private val cache: Cache,
	private val preferences: Preferences
) : CatalogRepository {
	override suspend fun getReleaseDetails(releaseId: Long): Result<Release> {
		val expiry: Long = 1_000 * 60 * 60
		val cacheKey = "_c_release_$releaseId"

	    cache.retrieve(cacheKey, ReleaseDto.serializer())?.let {
		    Log.e("REPO", "Cache Hit")
		    return Result.success(it.asRelease)
	    }

		val result = api.getReleaseDetails(releaseId)

		val releaseDto = result.getOrElse {
			Log.e("REPO", "Got Exception with result ${it.message}")
			return Result.failure(Exception("Track Not Found"))
		}

		cache.storeUntil(
			keyName = cacheKey,
			serializer = ReleaseDto.serializer(),
			data = releaseDto,
			expiresInMillis = expiry
		)
		return Result.success(releaseDto.asRelease)
	}
}
