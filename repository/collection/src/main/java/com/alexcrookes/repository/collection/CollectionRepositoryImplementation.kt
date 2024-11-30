package com.alexcrookes.repository.collection

import com.alexcrookes.core.entities.CollectionFolder
import com.alexcrookes.core.entities.CollectionRelease
import com.alexcrookes.core.extension.Epoch
import com.alexcrookes.provider.api.Api
import com.alexcrookes.provider.api.dto.CollectionFolderDto
import com.alexcrookes.provider.api.dto.CollectionReleaseDto
import com.alexcrookes.provider.api.response.CollectionFoldersResponse
import com.alexcrookes.provider.local_preferences.Preferences
import com.alexcrookes.provider.room.CollectionDao
import com.alexcrookes.repository.collection.extension.asCollectionFolder
import com.alexcrookes.repository.collection.extension.asCollectionRelease
import com.alexcrookes.repository.collection.extension.asCollectionReleaseRow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class CollectionRepositoryImplementation @Inject constructor(
	private val api: Api,
	private val collectionDao: CollectionDao,
	private val prefs: Preferences
): CollectionRepository {
	private var _folders = MutableStateFlow<List<CollectionFolderDto>>(emptyList())
	override val folders = _folders.asStateFlow()

	private var _collectionReleases = MutableStateFlow<List<CollectionReleaseDto>>(emptyList())
	override val releases = _collectionReleases.asStateFlow()

	private val syncKey = "_collection_sync_time"
	private var lastSyncTime: Epoch
		get() = prefs.readTimeStamp(syncKey)
		set(value) { prefs.writeTimeStamp(syncKey, value)}

	// Weak coupling...
	private val syncRequired: Boolean
		get() = lastSyncTime == 0L || _collectionReleases.value.isEmpty()

	override suspend fun getFolders(userName: String): Result<List<CollectionFolder>> {
		val response = api.getCollectionFolders("HiFiChild")

		val responseData: CollectionFoldersResponse = response.getOrElse {
			// @TODO - Log error
			return Result.failure( Exception("No Folder - ${it.message}"))
		}

		return Result.success(
			responseData.folders.map { it.asCollectionFolder }
		)
	}

	override suspend fun collectionContains(id: Long): Result<Boolean> {
		val count = collectionDao.collectionContains(id)
		return Result.success(count > 0)
	}

	override suspend fun TEST_insert(collectionRelease: CollectionRelease): Result<Boolean> {
		collectionDao.insert(collectionRelease.asCollectionReleaseRow)
		return Result.success(true)
	}

	override suspend fun getCollection(
		userName: String, folderId: Int
	): Result<List<CollectionRelease>> {
		val results = mutableListOf<CollectionRelease>()
		var currentPage = 0
		var lastPage = Int.MAX_VALUE

		while (currentPage <= lastPage) {
			currentPage += 1 // pages start at 1 (i.e. requesting page zero will always return page 1)
			try {
				val response = api.getCollectionReleasesForFolderWithPage(
					folderId, currentPage, userName
				)

				response.getOrNull()?.let { responseData ->
					lastPage = responseData.pagination.pages
					results.addAll(responseData.releases.map { it.asCollectionRelease })
				}
			} catch(e: Exception) {
				// @TODO - LOG
			}
		}

		lastSyncTime = System.currentTimeMillis()
		return Result.success(results)
	}

	private suspend fun loadCollectionFromLocalStorage() {
		val collection = collectionDao.getCollection().map {

		}
	}

	private suspend fun loadCollectionFromApi(
		userName: String, folderId: Int
	): List<CollectionRelease> {
		val results = mutableListOf<CollectionRelease>()
		var currentPage = 0
		var lastPage = Int.MAX_VALUE

		while (currentPage <= lastPage) {
			currentPage += 1 // pages start at 1 (i.e. requesting page zero will always return page 1)
			try {
				val response = api.getCollectionReleasesForFolderWithPage(
					folderId, currentPage, userName
				)

				response.getOrNull()?.let { responseData ->
					lastPage = responseData.pagination.pages
					results.addAll(responseData.releases.map { it.asCollectionRelease })
				}
			} catch(e: Exception) {
				// @TODO - LOG
			}
		}

		lastSyncTime = System.currentTimeMillis()
		return results.toList()
		//return Result.success(results)
	}


}
