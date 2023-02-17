package com.glima.ilovecats.feature.list

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glima.domain.model.Breed
import com.glima.domain.usecase.FetchBreedsUseCase
import kotlinx.coroutines.launch

class BreedListViewModel(
    private val fetchBreedsUseCase: FetchBreedsUseCase
) : ViewModel() {

    private val _breeds = mutableStateListOf(listOf<Breed>())
    val breeds: SnapshotStateList<List<Breed>>
        get() {
            return _breeds
        }

    init {
        viewModelScope.launch {
            _breeds.add(fetchBreedsUseCase())
        }
    }
}