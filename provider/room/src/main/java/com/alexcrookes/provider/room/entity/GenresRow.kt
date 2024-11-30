package com.alexcrookes.provider.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

internal const val GenresTable = "genres_table"
@Entity(tableName = GenresTable)
data class GenresRow(
	@PrimaryKey @ColumnInfo("genre_name")
	val genre: String
)



internal const val CollectionGenresCrossRefTable = "collection_genre_crossref_table"
@Entity(
	tableName = CollectionGenresCrossRefTable,
	primaryKeys = ["instance_id", "genre"]
)
data class CollectionGenresCrossRefRow(
	@ColumnInfo(name="instance_id")
	val instanceId: Long,

	@ColumnInfo(name="genre")
	val genre: String
)
