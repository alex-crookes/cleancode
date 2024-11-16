package com.alexcrookes.repository.catalog.extension

import com.alexcrookes.core.entities.RemoteImage
import com.alexcrookes.provider.api.dto.ImageDto

internal val ImageDto.asRemoteImage: RemoteImage?
	get() {
		require (uri.isNotEmpty()) {
			return null
		}

		return when (type.uppercase()) {
			"PRIMARY" ->
				RemoteImage.Primary(
					uri = uri,
					resourceUrl = resourceUrl,
					uri150 = uri150,
					width = width,
					height = height
				)

			"SECONDARY" ->
				RemoteImage.Secondary(
					uri = uri,
					resourceUrl = resourceUrl,
					uri150 = uri150,
					width = width,
					height = height
				)

			else ->
				null
		}
	}
