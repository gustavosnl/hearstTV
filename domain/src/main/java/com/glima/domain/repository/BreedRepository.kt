package com.glima.domain.repository

import com.glima.domain.model.Breed

interface BreedRepository {

    suspend fun fetchBreedsByPage(page: Int): List<Breed>
    suspend fun loadBreedDetail(breedId: String): Breed
    suspend fun loadBreedGallery(breedId: String): List<String>
}

