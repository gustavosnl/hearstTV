package com.glima.ilovecats.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn

class BreedListViewModel(
    private val breedListLogic: BreedListLogic
) : ViewModel() {

    val breeds = breedListLogic.loadNext().cachedIn(viewModelScope)

}