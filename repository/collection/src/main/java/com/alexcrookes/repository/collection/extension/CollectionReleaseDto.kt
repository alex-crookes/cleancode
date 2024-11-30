package com.alexcrookes.repository.collection.extension

import com.alexcrookes.core.entities.CollectionRelease
import com.alexcrookes.core.extension.iso8601ToEpoch
import com.alexcrookes.provider.api.dto.CollectionReleaseDto

internal val CollectionReleaseDto.asCollectionRelease: CollectionRelease
	get() {
		val listRelease = this.basicInformation.asListRelease

		return CollectionRelease(
			id = this.id,
			instanceId = this.instanceId,
			dateAdded = this.date.iso8601ToEpoch(),
			rating = this.rating,
			folder = this.folder,
			listRelease = listRelease,
		)
	}
