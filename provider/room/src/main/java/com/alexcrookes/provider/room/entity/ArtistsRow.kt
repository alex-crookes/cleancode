package com.alexcrookes.provider.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

internal const val ArtistsTable = "artists_table"
@Entity(tableName = ArtistsTable)
data class ArtistRow(
	@PrimaryKey @ColumnInfo("artist_id")
	val id: Long,

	@ColumnInfo("artist_name")
	val name: String
)


internal const val CollectionArtistsCrossRefTable = "collection_artists_crossref_table"
@Entity(tableName = CollectionArtistsCrossRefTable, primaryKeys = ["instance_id", "artist_id"])
data class CollectionArtistsCrossRefRow(
	@ColumnInfo(name="instance_id")
	val instanceId: Long,

	@ColumnInfo(name="artist_id")
	val artistId: Long,

	@ColumnInfo(name="name", index = true)
	val artistName: String
)
