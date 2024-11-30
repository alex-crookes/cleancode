package com.alexcrookes.provider.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey

internal const val StylesTable = "styles_table"
@Entity(tableName = StylesTable)
data class StylesRow(
	@PrimaryKey @ColumnInfo("style_name")
	val style: String
)

internal const val CollectionStyleCrossRefTable = "collection_styles_crossref_table"
@Entity(
	tableName = CollectionStyleCrossRefTable,
	primaryKeys = ["instance_id", "style"]
)
data class CollectionStylesCrossRefRow(
	@ColumnInfo(name = "instance_id")
	val instanceId: Long,
	@ColumnInfo(name = "style")
	val style: String
)
