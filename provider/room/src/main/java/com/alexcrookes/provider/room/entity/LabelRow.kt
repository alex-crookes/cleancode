package com.alexcrookes.provider.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

internal const val LabelsTable = "labels_table"
@Entity(
	tableName = LabelsTable,
)
data class LabelRow(
	@PrimaryKey @ColumnInfo("label_id")
	val id: Long,

	@ColumnInfo("instance_id", index = true)
	val instanceId: Long,

	@ColumnInfo("label_name")
	val name: String
)

internal const val CollectionLabelsCrossRefTable = "collection_labels_crossref_table"
@Entity(
	tableName = CollectionLabelsCrossRefTable,
	primaryKeys = ["instance_id", "label_id"]
)
data class CollectionLabelsCrossRefRow(
	@ColumnInfo(name="instance_id")
	val instanceId: Long,

	@ColumnInfo(name="label_id")
	val labelId: Long,

	@ColumnInfo(name="label_name")
	val name: String
)
