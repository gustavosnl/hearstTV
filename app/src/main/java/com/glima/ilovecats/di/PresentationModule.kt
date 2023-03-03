package com.glima.ilovecats.di

import com.glima.data.repository.BreedPagingSource
import com.glima.ilovecats.feature.detail.BreedDetailLogic
import com.glima.ilovecats.feature.detail.BreedDetailViewModel
import com.glima.ilovecats.feature.list.BreedListLogic
import com.glima.ilovecats.feature.list.BreedListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {
    val presentationModule = module {

        viewModel {
            BreedListViewModel(get())
        }

        viewModel { (breedId: String) ->
            BreedDetailViewModel(dispatcher = Dispatchers.Main, breedId = breedId, logic = get())
        }

        single {
            BreedListLogic(get())
        }

        single {
            BreedDetailLogic(get())
        }

        single {
            BreedPagingSource(get())
        }
    }
}