package com.alexcrookes.repository.collection.extension

import com.alexcrookes.core.entities.CollectionRelease
import com.alexcrookes.core.extension.iso8601ToEpoch
import com.alexcrookes.provider.api.dto.CollectionReleaseDto
import com.alexcrookes.provider.room.entity.ArtistRow
import com.alexcrookes.provider.room.entity.CollectionReleaseRow
import com.alexcrookes.provider.room.entity.CollectionReleaseRowWithMeta
import com.alexcrookes.provider.room.entity.FormatRow
import com.alexcrookes.provider.room.entity.GenresRow
import com.alexcrookes.provider.room.entity.LabelRow
import com.alexcrookes.provider.room.entity.StylesRow

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

internal val CollectionReleaseDto.asRow: CollectionReleaseRowWithMeta
	get() {
		val collectionReleaseRow = CollectionReleaseRow(
			instanceId = this.instanceId,
			id = this.id,
			dateAdded = this.date.iso8601ToEpoch(),
			rating = this.rating,
			folder = this.folder,
			masterId = this.basicInformation.masterId,
			resourceUrl = this.basicInformation.resourceUrl,
			thumbnail = this.basicInformation.thumbnail,
			coverImage = this.basicInformation.coverImage,
			title = this.basicInformation.title,
			year = this.basicInformation.year
		)

		val formats: List<FormatRow> = this.basicInformation.formats.map {
			FormatRow(format = it.name)
		}

		val artists: List<ArtistRow> = this.basicInformation.artists.map {
			ArtistRow(id = it.id, name = it.name)
		}

		val genres: List<GenresRow> = this.basicInformation.genres.map {
			GenresRow(genre = it)
		}

		val labels: List<LabelRow> = this.basicInformation.labels.map {
			LabelRow(id = it.id, instanceId = it.instanceId, name = it.name)
		}

		val styles = this.basicInformation.styles.map {
			StylesRow(style = it)
		}

		return CollectionReleaseRowWithMeta(
			row = collectionReleaseRow,
			formats = formats,
			artists = artists,
			genres = genres,
			labels = labels,
			styles = styles
		)
	}
