package com.glima.data.repository

import com.glima.data.domain.mapper.BreedMapper
import com.glima.domain.model.Breed

class BreedRepositoryImpl(val api: CatApi) : BreedRepository {

    override suspend fun fetchBreedsByPage(page: Int): ApiResponse<List<Breed>> {
        val result = api.fetchBreedsByPage(page)

        return if (result.isSuccessful) {
            val breeds = result.body()?.map { BreedMapper.toDomain(it) } ?: emptyList()
            ApiResponse.Success(breeds)
        } else {
            ApiResponse.Error(message = result.errorBody().toString())
        }
    }

    override suspend fun loadBreedDetail(breedId: String): ApiResponse<Breed>? {

        val result = api.fetchBreedDetail(breedId)
        return if (result.isSuccessful) {
            result.body()?.let { ApiResponse.Success(BreedMapper.toDomain(it)) }
        } else {
            ApiResponse.Error(message = result.errorBody().toString())
        }
    }

    override suspend fun loadBreedGallery(breedId: String): ApiResponse<List<String>> {

        val result = api.getBreedImage(breedId)
        return if (result.isSuccessful) {
            val imageUrls = result.body()?.map { it.url } ?: emptyList()
            ApiResponse.Success(imageUrls)
        } else {
            ApiResponse.Error(message = result.errorBody().toString())
        }
    }
}
