package com.glima.ilovecats.feature.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.glima.domain.model.Breed
import org.koin.androidx.compose.getViewModel

@Composable
fun BreedItem(breed: Breed, navController: NavHostController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                navController.navigate("breed_detail/${breed.id}")
            }
    ) {
        Text(breed.name)
    }
}

@Composable
fun BreedList(viewModel: BreedListViewModel = getViewModel(), navController: NavHostController) {

    val lazyBreeds = viewModel.breeds.collectAsLazyPagingItems()

    LazyColumn {
        items(lazyBreeds) { breed ->
            BreedItem(breed = breed!!, navController)
        }
    }
}
