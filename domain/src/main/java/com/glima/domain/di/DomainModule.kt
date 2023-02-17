package com.glima.domain.di

import com.glima.domain.usecase.FetchBreedsUseCase
import com.glima.domain.usecase.FetchBreedsUseCaseImpl
import org.koin.dsl.module

object DomainModule {
    val domainModule = module {
        single<FetchBreedsUseCase> {
            FetchBreedsUseCaseImpl(repository = get())
        }
    }
}