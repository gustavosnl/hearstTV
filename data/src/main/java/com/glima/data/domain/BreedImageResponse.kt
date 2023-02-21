package com.glima.data.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BreedImageResponse(
    @Json(name = "id") val id: String,
    @Json(name = "url") val url: String
)