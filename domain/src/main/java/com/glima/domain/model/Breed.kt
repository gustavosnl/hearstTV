package com.glima.domain.model

data class Breed(
    val id: String,
    val name: String,
    val description: String,
    val lifeSpan: String,
    val isRare: Boolean,
    val affectionLevel: Int,
    val intelligence: Int
)