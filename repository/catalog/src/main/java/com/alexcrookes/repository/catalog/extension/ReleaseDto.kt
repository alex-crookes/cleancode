package com.alexcrookes.repository.catalog.extension

import com.alexcrookes.core.entities.Community
import com.alexcrookes.core.entities.Rating
import com.alexcrookes.core.entities.Release
import com.alexcrookes.provider.api.dto.ReleaseDto

internal val ReleaseDto.asRelease: Release
	get() = Release(
		id = this.id,
		title = this.title,
		artists = this.artists.map { it.name },
		year = this.year,
		uri = this.uri,
		resourceUrl = this.resourceUrl,
		genres = this.genres,
		styles = this.styles,
		images = this.images.mapNotNull { it.asRemoteImage },
		tracks = this.tracks.map { it.asTrack },
		videos = this.videos.map { it.asVideo },
		description = this.notes,
		community = Community(
			wantedBy = this.community.want,
			ownedBy = this.community.have,
			rating = Rating(
				count = this.community.rating.ratingCount,
				average = this.community.rating.averageRating
			)
		)
	)
