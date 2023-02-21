package com.glima.ilovecats.feature.detail

import com.glima.domain.model.Breed
import com.glima.domain.repository.BreedRepository

class BreedDetailLogic(private val repository: BreedRepository) {

    suspend fun loadBreedDetail(breedId: String): Breed {
        return repository.loadBreedDetail(breedId)
    }

    suspend fun loadGallery(breedId: String): List<String> {
        return repository.loadBreedGallery(breedId)
    }
}