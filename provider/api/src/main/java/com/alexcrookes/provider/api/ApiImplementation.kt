package com.alexcrookes.provider.api

import com.alexcrookes.core.coreJsonSettings
import com.alexcrookes.provider.api.dto.ReleaseDto
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


	// endregion

	// region Implementations (Api)

	init {
		val token = prefs.readFromSerializationSync(String.serializer(), authTokenKeyName)
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
			val clientAuth = "Discogs key=$consumerKey, secret=$consumerSecret"
			onRequest { request, _ ->
				request.headers.append("User-Agent", userAgentValue)
				request.headers.append("Authorization", clientAuth)
				personalToken?.let {
					val headerValue = "Discogs token=$it"
					request.headers.append("Authorization", headerValue)
				}
			}
		}

		httpClient = HttpClient {
			install(ContentNegotiation) { json(coreJsonSettings) }
			install(plugin)
		}
	}

	// endregion
}
