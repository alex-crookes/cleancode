package com.alexcrookes.repository.collection.fakes

import com.alexcrookes.provider.api.Api
import com.alexcrookes.provider.api.dto.CollectionReleaseDto
import com.alexcrookes.provider.api.dto.ReleaseDto
import com.alexcrookes.provider.api.response.CollectionFoldersResponse
import com.alexcrookes.provider.api.response.CollectionResponse
import com.alexcrookes.provider.fileio.FileIO
import com.alexcrookes.provider.fileio.FileIOImplementation

class FakeApi: Api {
	private val fileIO: FileIO = FileIOImplementation()

	override suspend fun clearCredentials() {
		//TODO("Not yet implemented")
	}

	override suspend fun authenticate(authToken: String) {
		//TODO("Not yet implemented")
	}

	override suspend fun getReleaseDetails(id: Long): Result<ReleaseDto> {
		TODO("Not yet implemented")
	}

	override suspend fun getReleaseDetails(id: Long, currency: String): Result<ReleaseDto> {
		TODO("Not yet implemented")
	}

	override suspend fun getCollectionFolders(userName: String): Result<CollectionFoldersResponse> {
		val data = fileIO.readClassFromResources(
			"/responses/folders.json", CollectionFoldersResponse.serializer()
		)

		return data?.let {
			Result.success(it)
		} ?: run {
			Result.failure( Exception("No File"))
		}

	}

	override suspend fun getCollectionReleasesForFolder(
		folderId: Int,
		userName: String,
	): Result<CollectionResponse> {
		val data = fileIO.readClassFromResources(
			"/responses/collection_1.json", CollectionResponse.serializer()
		)

		return data?.let {
			Result.success(it)
		} ?: run {
			Result.failure( Exception("No File"))
		}
	}

	override suspend fun getCollectionReleasesForFolderWithPage(
		folderId: Int,
		page: Int,
		userName: String,
	): Result<CollectionResponse> {
		val file = when (page) {
			0, 1 -> "/responses/collection_1.json"
			2 -> "/responses/collection_2.json"
			3 -> "/responses/collection_3.json"
			4 -> "/responses/collection_4.json"
			5 -> "/responses/collection_5.json"
			6 -> "/responses/collection_6.json"
			7 -> "/responses/collection_7.json"
			else -> "/responses/collection_1.json"
		}

		val data = fileIO.readClassFromResources(
			file, CollectionResponse.serializer()
		)

		return data?.let {
			Result.success(it)
		} ?: run {
			Result.failure( Exception("No File"))
		}
	}

	override suspend fun getAllCollectionReleasesForFolder(
		folderId: Int,
		userName: String,
	): Result<List<CollectionReleaseDto>> {
		TODO("Not yet implemented")
	}


}
