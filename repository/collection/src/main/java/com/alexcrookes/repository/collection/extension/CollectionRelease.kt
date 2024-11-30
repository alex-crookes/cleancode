package com.alexcrookes.repository.collection.extension

import com.alexcrookes.core.entities.CollectionRelease
import com.alexcrookes.provider.room.entity.CollectionReleaseRow

internal val CollectionRelease.asCollectionReleaseRow: CollectionReleaseRow
	get() = CollectionReleaseRow(
		id = this.id,
		instanceId = this.instanceId,
		dateAdded = this.dateAdded,
		rating = this.rating,
		folder = this.folder,
		masterId = this.listRelease.masterId,
		resourceUrl = this.listRelease.resourceUrl,
		thumbnail = this.listRelease.thumbnail,
		coverImage = this.listRelease.coverImage,
		title = this.listRelease.title,
		year = this.listRelease.year,
	)
