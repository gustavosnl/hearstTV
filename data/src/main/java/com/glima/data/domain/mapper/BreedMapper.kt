package com.glima.data.domain.mapper

import com.glima.data.domain.BreedResponse
import com.glima.domain.model.Breed

object BreedMapper {

    fun toDomain(breedResponse: BreedResponse) = with(breedResponse) {
        Breed(
            id = id,
            name = name,
            description = description,
            lifeSpan = lifeSpan,
            isRare = isRare == 1,
            affectionLevel = affectionLevel,
            intelligence = intelligence
        )
    }
}