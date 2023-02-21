package com.glima.ilovecats.di

import com.glima.data.repository.BreedPagingSource
import com.glima.ilovecats.feature.detail.BreedDetailViewModel
import com.glima.ilovecats.feature.list.BreedListLogic
import com.glima.ilovecats.feature.list.BreedListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {
    val presentationModule = module {

        viewModel {
            BreedListViewModel(get())
        }

        viewModel { (breedId: String) ->
            BreedDetailViewModel(breed = breedId)
        }

        single {
            BreedListLogic(get())
        }

        single {
            BreedPagingSource(get())
        }
    }
}