package com.alexcrookes.provider.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

internal const val FormatsTable = "formats_table"
@Entity(tableName = FormatsTable)
data class FormatRow(
	@PrimaryKey @ColumnInfo("format_name")
	val format: String
)

internal const val CollectionFormatsCrossRefTable = "collection_format_crossref_table"
@Entity(
	tableName = CollectionFormatsCrossRefTable,
	primaryKeys = ["instance_id", "format_name"],
)

data class CollectionFormatsCrossRefRow(
	@ColumnInfo(name = "instance_id")
	val instanceId: Long,

	@ColumnInfo(name = "format_name", index = true)
	val format: String
)
