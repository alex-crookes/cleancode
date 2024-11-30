package com.alexcrookes.provider.api.response

import com.alexcrookes.provider.api.dto.CollectionFolderDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionFoldersResponse(
	@SerialName("folders")
	val folders: List<CollectionFolderDto>
)
