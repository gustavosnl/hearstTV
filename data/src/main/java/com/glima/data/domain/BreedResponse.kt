package com.glima.data.domain

import com.squareup.moshi.Json

data class BreedResponse(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "life_span") val lifeSpan: String,
    @Json(name = "rare") val isRare: Int,
    @Json(name = "affection_level") val affectionLevel: Int,
    @Json(name = "intelligence") val intelligence: Int
)