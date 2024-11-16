package com.alexcrookes.repository.catalog.extension

import com.alexcrookes.core.entities.Video
import com.alexcrookes.provider.api.dto.ReleaseVideoDto

val ReleaseVideoDto.asVideo: Video
	get() {
		return when {
			this.url.contains("youtube") -> {
				val id = url.split("v=")[1]

				require (id.isNotEmpty()) {
					return Video.Other(
						_url = this.url,
						_title = this.title,
						_description = this.description,
						_duration = this.duration,
						_embed = this.embed
					)
				}

				Video.YouTube(
					id = id,
					_url = this.url,
					_title = this.title,
					_description = this.description,
					_duration = this.duration,
					_embed = this.embed
				)
			}

			else -> Video.Other(
				_url = this.url,
				_title = this.title,
				_description = this.description,
				_duration = this.duration,
				_embed = this.embed
			)
		}
	}
