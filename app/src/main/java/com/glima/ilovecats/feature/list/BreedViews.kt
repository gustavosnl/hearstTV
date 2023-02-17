package com.glima.ilovecats.feature.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.glima.domain.model.Breed

@Composable
fun BreedItem(breed: Breed) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(4.dp)
    ) {
        Text(breed.name)
    }
}

@Composable
fun BreedList(viewModel: BreedListViewModel) {
    val lazyBreeds = viewModel.breeds.collectAsLazyPagingItems()

    LazyColumn {
        items(lazyBreeds) { breed ->
            BreedItem(breed = breed!!)
        }
    }
}
