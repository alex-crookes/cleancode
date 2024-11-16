package com.alexcrookes.repository.catalog.extension

import com.alexcrookes.core.entities.Track
import com.alexcrookes.core.entities.TrackType
import com.alexcrookes.provider.api.dto.ReleaseTrackDto

internal val ReleaseTrackDto.asTrack: Track
	get() = Track(
		title = this.title,
		duration = this.duration,
		extraArtists = this.extraArtists.map { it.name },
		position = this.position,
		type = TrackType.fromApiName(this.type)
	)
