package com.glima.ilovecats.feature.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glima.domain.model.Breed
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

sealed class BreedDetailState {
    object Loading : BreedDetailState()
    class Loaded(val breed: Breed) : BreedDetailState()
    object Error : BreedDetailState()
}

sealed class BreedGalleryState {
    object Loading : BreedGalleryState()
    object Error : BreedGalleryState()
    class Loaded(val imageUrls: List<String>) : BreedGalleryState()
}

class BreedDetailViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val breedId: String,
    private val logic: BreedDetailLogic
) : ViewModel() {

    private val _breed = mutableStateOf<BreedDetailState>(BreedDetailState.Loading)
    val breedDetail: State<BreedDetailState> = _breed

    private val _breedGallery = mutableStateOf<BreedGalleryState>(BreedGalleryState.Loading)
    val breedGallery: State<BreedGalleryState> = _breedGallery


    init {
        loadBreedInfo()
        loadBreedGallery()
    }

    fun loadBreedGallery() {
        viewModelScope.launch(dispatcher) {
            _breedGallery.value = logic.loadGallery(breedId)
        }
    }

    fun loadBreedInfo() {
        viewModelScope.launch(dispatcher) {
            _breed.value = logic.loadBreedDetail(breedId)
        }
    }
}