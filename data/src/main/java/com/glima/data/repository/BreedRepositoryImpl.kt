package com.glima.data.repository

import com.glima.data.domain.mapper.BreedMapper
import com.glima.domain.model.Breed
import com.glima.domain.repository.BreedRepository

class BreedRepositoryImpl(val api: CatApi) : BreedRepository {

    override suspend fun fetchBreedsByPage(page: Int): List<Breed> {
        return api.fetchBreedsByPage(page)
            .map { BreedMapper.toDomain(it) }
    }
}
