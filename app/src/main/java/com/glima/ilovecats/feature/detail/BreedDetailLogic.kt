package com.glima.ilovecats.feature.detail

import com.glima.data.repository.ApiResponse
import com.glima.data.repository.BreedRepository

class BreedDetailLogic(private val repository: BreedRepository) {

    suspend fun loadBreedDetail(breedId: String): BreedDetailState {

        return when (val response = repository.loadBreedDetail(breedId)) {
            is ApiResponse.Success -> {
                if (response.data != null)
                    BreedDetailState.Loaded(response.data!!)
                else
                    BreedDetailState.Error
            }
            else -> BreedDetailState.Error
        }
    }

    suspend fun loadGallery(breedId: String): BreedGalleryState {
        return when (val response = repository.loadBreedGallery(breedId)) {
            is ApiResponse.Success -> {
                if (response.data.isNullOrEmpty()) {
                    BreedGalleryState.Error
                } else {
                    BreedGalleryState.Loaded(response.data!!)
                }
            }
            else -> BreedGalleryState.Error
        }
    }
}