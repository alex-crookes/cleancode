package com.alexcrookes.provider.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

internal const val ListReleasesTable = "list_release_table"

@Entity(tableName = ListReleasesTable)
data class ListReleaseRow(
	@PrimaryKey @ColumnInfo(name="id") val id: Long,
	@ColumnInfo(name="master_id") val masterId: Long,
	@ColumnInfo(name="resource_url") val resourceUrl: String,
	@ColumnInfo(name="thumbnail") val thumbnail: String,
	@ColumnInfo(name="cover_image") val coverImage: String,
	@ColumnInfo(name="title") val title: String,
	@ColumnInfo(name="year") val year: Int,

//	@ColumnInfo(name="formats") val formats: List<String>,
//	@ColumnInfo(name="artists") val artists: List<String>,
//	@ColumnInfo(name="labels") val labels: List<String>,
//	@ColumnInfo(name="genres") val genres: List<String>,
//	@ColumnInfo(name="styles") val styles: List<String>

)

@Entity(tableName = "list_release_data_table")
data class ListReleaseData(
	@PrimaryKey @ColumnInfo(name="id") val id: Long,
	@ColumnInfo(name="master_id") val masterId: Long,
	@ColumnInfo(name="resource_url") val resourceUrl: String,
	@ColumnInfo(name="thumbnail") val thumbnail: String,
	@ColumnInfo(name="cover_image") val coverImage: String,
	@ColumnInfo(name="title") val title: String,
	@ColumnInfo(name="year") val year: Int,
)
