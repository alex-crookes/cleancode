package com.alexcrookes.provider.api

import com.alexcrookes.core.coreJsonSettings
import com.alexcrookes.provider.api.dto.CollectionReleaseDto
import com.alexcrookes.provider.api.dto.ReleaseDto
import com.alexcrookes.provider.api.response.CollectionFoldersResponse
import com.alexcrookes.provider.api.response.CollectionResponse
import com.alexcrookes.provider.local_preferences.Preferences
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLBuilder
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.builtins.serializer
import javax.inject.Inject

// @TODO - Add Observed properties to Preferences? i.e. can we watch the Preferences values?
class ApiImplementation @Inject constructor(
	private val prefs: Preferences,
	private val consumerKey: String,
	private val consumerSecret: String,
) : Api {

	// region Properties

	private val rootPath = "https://api.discogs.com"
	private val pageSize = 50
	private val authTokenKeyName = "__discogsAuthToken"
	private lateinit var httpClient: HttpClient

	//private val userName = "hifichild"

	// endregion

	// region Implementations (Api)

	init {
		//val token = prefs.readFromSerializationSync(String.serializer(), authTokenKeyName)
		val token = "RrUEkpDRsolZbtIPXVESHVLqhBqnvyKYmnBZmHMd"
		buildClient(token)
	}


	override suspend fun clearCredentials() {
		prefs.clearKey(authTokenKeyName)
		buildClient(null)
	}

	override suspend fun authenticate(authToken: String) {
		prefs.writeWithSerialization(String.serializer(), authTokenKeyName, authToken)
		buildClient(authToken)
	}

	override suspend fun getReleaseDetails(id: Long): Result<ReleaseDto> {
		try {
			val path = "$rootPath/releases/$id"
			val url = URLBuilder(path).build()
			val result = httpClient.get(url)

			require(result.status.value == HttpStatusCode.OK.value) {
				return Result.failure(Exception(result.status.toString()))
			}

			return Result.success(result.body<ReleaseDto>())
		} catch (e: Exception) {
			return Result.failure(Exception("Caught = ${e.message}"))
		}

	}

	override suspend fun getReleaseDetails(id: Long, currency: String): Result<ReleaseDto> {
		val path = "$rootPath/releases/$id?$currency"
		val url = URLBuilder(path).build()
		val result = httpClient.get(url)

		return parseResult<ReleaseDto>(result)
	}

	override suspend fun getCollectionFolders(userName: String): Result<CollectionFoldersResponse> {
		val path = "$rootPath/users/$userName/collection/folders"
		val url = URLBuilder(path).build()
		val result = httpClient.get(url)

		return parseResult<CollectionFoldersResponse>(result)
	}

	override suspend fun getCollectionReleasesForFolder(folderId: Int, userName: String): Result<CollectionResponse> {
		val path = "$rootPath/users/$userName/collection/folders/$folderId/releases?page_size=$pageSize"
		val url = URLBuilder(path).build()
		val result = httpClient.get(url)

		return parseResult<CollectionResponse>(result)
	}

	override suspend fun getAllCollectionReleasesForFolder(
		folderId: Int,
		userName: String
	): Result<List<CollectionReleaseDto>> {
		var page = 0
		var lastPage = Int.MAX_VALUE
		val results = mutableListOf<CollectionReleaseDto>()

		while (page <= lastPage) {
			page+= 1 // pages as 1 indexed, which is annoying
			val response = getCollectionReleasesForFolderWithPage(folderId, page, userName)
			response.getOrNull()?.let { responseData ->
				lastPage = responseData.pagination.pageSize
				results.addAll(responseData.releases)
			}

			require (lastPage < Int.MAX_VALUE) { // otherwise we'll lop endlessly
				return Result.failure( Exception("Initial Page not found") )
			}
		}

		return Result.success(results)
	}

	override suspend fun getCollectionReleasesForFolderWithPage(
		folderId: Int,
		page: Int,
		userName: String
	): Result<CollectionResponse> {
		val path = "$rootPath/users/$userName/collection/folders/$folderId/releases?page=$page&page_size=$pageSize"
		val url = URLBuilder(path).build()
		val result = httpClient.get(url)

		return parseResult<CollectionResponse>(result)
	}

	// endregion

	// region Helpers

	private suspend inline fun <reified T> parseResult(response: HttpResponse): Result<T> {
		return when (response.status) {
			HttpStatusCode.OK ->
				Result.success(response.body<T>())

			HttpStatusCode.NotFound ->
				Result.failure(Exception("Not Found"))

			else ->
				Result.failure(Exception("Other"))
		}
	}

	private fun buildClient(personalToken: String?) {
		val plugin = createClientPlugin("DiscogsTokenAuthPlugin") {
			val userAgentValue = "CleanCode/1.0 + https://alexcrookes.com"

			val authKey: String = personalToken?.let {
				"Discogs token=$it"
			} ?: run {
				"Discogs key=$consumerKey, secret=$consumerSecret"
			}
			//val clientAuth = "Discogs key=$consumerKey, secret=$consumerSecret"
			onRequest { request, _ ->
				request.headers.append("User-Agent", userAgentValue)
				request.headers.append("Authorization", authKey)
			}
		}

		httpClient = HttpClient {
			install(ContentNegotiation) { json(coreJsonSettings) }
			install(plugin)
		}
	}

	// endregion
}
