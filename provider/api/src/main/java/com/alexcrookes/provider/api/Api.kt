package com.alexcrookes.provider.api

import com.alexcrookes.provider.api.dto.CollectionReleaseDto
import com.alexcrookes.provider.api.dto.ReleaseDto
import com.alexcrookes.provider.api.response.CollectionFoldersResponse
import com.alexcrookes.provider.api.response.CollectionResponse

interface Api {
	/**
	 * Allows the API Credentials to be cleaned - Essentially Deauthenticate
	 */
	suspend fun clearCredentials()

	/**
	 * Allows the setting of API Credentials - This would normally not a function here
	 *  and instead would be set internally via na Authentication API call
	 */
	suspend fun authenticate(authToken: String)

	suspend fun getReleaseDetails(id: Long): Result<ReleaseDto>

	suspend fun getReleaseDetails(id: Long, currency: String): Result<ReleaseDto>

	suspend fun getCollectionFolders(userName: String): Result<CollectionFoldersResponse>

	suspend fun getCollectionReleasesForFolder(folderId: Int, userName: String): Result<CollectionResponse>

	suspend fun getCollectionReleasesForFolderWithPage(folderId: Int, page: Int, userName: String): Result<CollectionResponse>

	suspend fun getAllCollectionReleasesForFolder(folderId: Int, userName: String): Result<List<CollectionReleaseDto>>

	/**
	 * Request an AuthToken from API
	 *
	 * @sample [GET] https://api.discogs.com/oauth/request_token
	 */
	//suspend fun requestOauthToken()



}
