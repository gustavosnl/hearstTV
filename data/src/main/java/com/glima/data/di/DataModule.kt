package com.glima.data.di

import com.glima.data.repository.BreedRepositoryImpl
import com.glima.domain.repository.BreedRepository
import org.koin.dsl.module

object DataModule {
    val dataModule = module {
        single<BreedRepository> {
            BreedRepositoryImpl(api = get())
        }
    }
}
