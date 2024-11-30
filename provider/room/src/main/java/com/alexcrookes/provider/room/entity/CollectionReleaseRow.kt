package com.alexcrookes.provider.room.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

internal const val CollectionTable = "collection"

@Entity(tableName = CollectionTable)
data class CollectionReleaseRow(
	@PrimaryKey @ColumnInfo(name="instance_id")
	val instanceId: Long,

	@ColumnInfo(name="id", index = true)
	val id: Long,

	@ColumnInfo(name="date_added")
	val dateAdded: Long,

	@ColumnInfo(name="rating")
	val rating: Double,

	@ColumnInfo(name="folder")
	val folder: Int,

	@ColumnInfo(name="master_id")
	val masterId: Long,

	@ColumnInfo(name="resource_url")
	val resourceUrl: String,

	@ColumnInfo(name="thumbnail")
	val thumbnail: String,

	@ColumnInfo(name="cover_image")
	val coverImage: String,

	@ColumnInfo(name="title")
	val title: String,

	@ColumnInfo(name="year")
	val year: Int,



//	@Relation(
//		parentColumn = ""
//	)
//	val formats: List<String>
)

data class CollectionReleaseRowWithMeta(
	@Embedded
	val row: CollectionReleaseRow,

	@Relation(
		parentColumn = "instance_id",
		entityColumn = "format_name",
		associateBy = Junction(CollectionFormatsCrossRefRow::class)
	)
	val formats: List<FormatRow>,

	@Relation(
		parentColumn = "instance_id",
		entityColumn = "id",
		associateBy = Junction(CollectionArtistsCrossRefRow::class)
	)
	val artists: List<ArtistRow>,

	@Relation(
		parentColumn = "instance_id",
		entityColumn = "id",
		associateBy = Junction(CollectionGenresCrossRefRow::class)
	)
	val genres: List<GenresRow>,

	@Relation(
		parentColumn = "instance_id",
		entityColumn = "label_id",
		associateBy = Junction(CollectionLabelsCrossRefRow::class)
	)
	val labels: List<LabelRow>,

	@Relation(
		parentColumn = "instance_id",
		entityColumn = "style",
		associateBy = Junction(CollectionStylesCrossRefRow::class)
	)
	val styles: List<StylesRow>
)
