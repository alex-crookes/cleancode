package com.alexcrookes.provider.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LabelDto(
	@SerialName("name") val name: String, //: "RCA",
	@SerialName("catno") val catalogueNumber: String, //: "PB 41447",
	@SerialName("entity_type") val entityType: String, //: //"1",
	@SerialName("entity_type_name") val entityTypeName: String, //: "Label",
	@SerialName("id") val id: Long, //: 895,
	@SerialName("resource_url") val resourceUrl: String, //: "https://api.discogs.com/labels/895"
)
