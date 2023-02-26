package com.glima.data.repository

import com.glima.domain.model.Breed

interface BreedRepository {

    suspend fun fetchBreedsByPage(page: Int): ApiResponse<List<Breed>>
    suspend fun loadBreedDetail(breedId: String): ApiResponse<Breed>?
    suspend fun loadBreedGallery(breedId: String): ApiResponse<List<String>>
}

