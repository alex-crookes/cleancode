package com.alexcrookes.repository.collection

import com.alexcrookes.core.entities.CollectionFolder
import com.alexcrookes.core.entities.CollectionRelease
import com.alexcrookes.provider.api.dto.CollectionFolderDto
import com.alexcrookes.provider.api.dto.CollectionReleaseDto
import kotlinx.coroutines.flow.StateFlow

interface CollectionRepository {
	val folders: StateFlow<List<CollectionFolderDto>>

	val releases: StateFlow<List<CollectionReleaseDto>>

	suspend fun getFolders(userName: String): Result<List<CollectionFolder>>

	suspend fun getCollection(userName: String, folderId: Int): Result<List<CollectionRelease>>

	@Deprecated("This should not be in this interface")
	suspend fun TEST_insert(collectionRelease: CollectionRelease): Result<Boolean>

	suspend fun collectionContains(id: Long): Result<Boolean>
}


/*
TODO
	- Add support for Many to Many tables (i.e. Labels, Genres, /etc
		- return as Lists
		- How to ensure it inserts?
	- Add Migration to include new tables
	- Add Flow versions of above methods


 */
