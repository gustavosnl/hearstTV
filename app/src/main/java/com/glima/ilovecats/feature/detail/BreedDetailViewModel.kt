package com.glima.ilovecats.feature.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glima.domain.model.Breed
import kotlinx.coroutines.launch

sealed class BreedDetailState {
    object Loading : BreedDetailState()
    class Loaded(val breed: Breed) : BreedDetailState()
}

class BreedDetailViewModel(private val breedId: String, private val logic: BreedDetailLogic) :
    ViewModel() {

    private val _breed = mutableStateOf<BreedDetailState>(BreedDetailState.Loading)
    val breedDetail: State<BreedDetailState> = _breed

    init {
        viewModelScope.launch {
            _breed.value = BreedDetailState.Loaded(logic.loadBreedDetail(breedId))
        }
    }
}