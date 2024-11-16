package com.alexcrookes.provider.api

import com.alexcrookes.provider.api.dto.ReleaseDto

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

}
