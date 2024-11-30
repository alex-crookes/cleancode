package com.alexcrookes.repository.collection.extension

import com.alexcrookes.core.entities.CollectionFolder
import com.alexcrookes.provider.api.dto.CollectionFolderDto

internal val CollectionFolderDto.asCollectionFolder: CollectionFolder
	get() = CollectionFolder(
		id = this.id,
		name = this.name,
		itemCount = this.count,
		resourceUrl = this.resourceUrl
	)
