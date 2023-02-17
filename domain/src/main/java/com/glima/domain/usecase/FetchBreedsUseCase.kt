package com.glima.domain.usecase

import com.glima.domain.model.Breed
import com.glima.domain.repository.BreedRepository

interface FetchBreedsUseCase {

    suspend operator fun invoke(page: Int = 0): List<Breed>
}

class FetchBreedsUseCaseImpl(private val repository: BreedRepository) : FetchBreedsUseCase {
    override suspend fun invoke(page: Int): List<Breed> {
        return repository.fetchBreedsByPage(page = page)
    }
}