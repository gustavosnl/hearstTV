package com.glima.ilovecats.feature.list

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
//    val breeds = viewModel.breeds.re()
//
//    LazyColumn {
//        items(breeds.value) { breed ->
//            BreedItem(breed = breed)
//        }
//    }
}
