package com.alexcrookes.repository.collection.extension

import com.alexcrookes.core.entities.CollectionRelease
import com.alexcrookes.core.entities.ListRelease
import com.alexcrookes.provider.room.entity.CollectionReleaseRow

internal val CollectionReleaseRow.asCollectionRelease: CollectionRelease
	get() = CollectionRelease(
		id = this.id,
		instanceId = this.instanceId,
		dateAdded = this.dateAdded,
		rating = this.rating,
		folder = this.folder,
		listRelease = ListRelease(
			id = this.id,
			masterId = this.masterId,
			resourceUrl = this.resourceUrl,
			thumbnail = this.thumbnail,
			coverImage = this.coverImage,
			title = this.title,
			year = this.year,
			formats = emptyList(),
			artists = emptyList(),
			labels = emptyList(),
			genres = emptyList(),
			styles = emptyList(),
		)
	)
