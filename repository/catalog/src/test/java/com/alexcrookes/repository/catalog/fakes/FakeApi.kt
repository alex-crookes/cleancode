package com.alexcrookes.repository.catalog.fakes

import com.alexcrookes.provider.api.Api
import com.alexcrookes.provider.api.dto.CollectionReleaseDto
import com.alexcrookes.provider.api.dto.ReleaseDto
import com.alexcrookes.provider.api.response.CollectionFoldersResponse
import com.alexcrookes.provider.api.response.CollectionResponse
import com.alexcrookes.provider.fileio.FileIO
import com.alexcrookes.provider.fileio.FileIOImplementation

class FakeApi : Api {
	private val fileIO: FileIO = FileIOImplementation()

	var credentials: String? = null

	override suspend fun clearCredentials() {
		credentials = null
	}

	override suspend fun authenticate(authToken: String) {
		credentials = authToken
	}

	override suspend fun getReleaseDetails(id: Long): Result<ReleaseDto> {
		val data: ReleaseDto? = when (id) {
			249504L -> fileIO.readClassFromResources(
				"/dto/release-249504-code200.json", ReleaseDto.serializer()
			)

			else -> null
		}

		data?.let { return Result.success(it) }
			?: run { return Result.failure(Exception("no file"))}
	}

	override suspend fun getReleaseDetails(id: Long, currency: String): Result<ReleaseDto> {
		return getReleaseDetails(id)
	}

	override suspend fun getCollectionFolders(userName: String): Result<CollectionFoldersResponse> {
		TODO("Not yet implemented")
	}

	override suspend fun getCollectionReleasesForFolder(
		folderId: Int,
		userName: String,
	): Result<CollectionResponse> {
		TODO("Not yet implemented")
	}

	override suspend fun getCollectionReleasesForFolderWithPage(
		folderId: Int,
		page: Int,
		userName: String,
	): Result<CollectionResponse> {
		TODO("Not yet implemented")
	}

	override suspend fun getAllCollectionReleasesForFolder(
		folderId: Int,
		userName: String,
	): Result<List<CollectionReleaseDto>> {
		TODO("Not yet implemented")
	}
}
