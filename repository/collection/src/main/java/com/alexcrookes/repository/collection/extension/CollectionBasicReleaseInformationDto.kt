package com.alexcrookes.repository.collection.extension

import com.alexcrookes.core.entities.ListRelease
import com.alexcrookes.provider.api.dto.CollectionBasicReleaseInformationDto

internal val CollectionBasicReleaseInformationDto.asListRelease: ListRelease
	get() = ListRelease(
		id = this.id,
		masterId = this.masterId,
		resourceUrl = this.resourceUrl,
		thumbnail = this.thumbnail,
		coverImage = this.coverImage,
		title = this.title,
		year = this.year,
		formats = this.formats.map { it.name },
		artists = this.artists.map { it.name },
		labels = this.labels.map { it.name },
		genres = this.genres,
		styles = this.styles
	)
