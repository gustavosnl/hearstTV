package com.glima.ilovecats.feature.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.glima.domain.model.Breed
import com.glima.ilovecats.R
import com.glima.ilovecats.Screen
import org.koin.androidx.compose.getViewModel

@Composable
fun BreedItem(breed: Breed, navController: NavHostController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(4.dp)
            .clickable {
                navController.navigate("breed_detail/${breed.id}")
            }
    ) {
        BreedIcon(breed)
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = breed.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Composable
fun BreedIcon(breed: Breed) {
    val breedIcon = if (breed.isRare) {
        R.drawable.ic_rare_breeding
    } else {
        R.drawable.ic_paw_filled
    }
    Image(
        imageVector = ImageVector.vectorResource(id = breedIcon),
        contentDescription = null,
        modifier = Modifier.size(32.dp)
    )
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

@Composable
fun BreedListScreen(navController: NavHostController) {
    Column() {
        TopAppBar(title = { Text(text = stringResource(id = Screen.BreedList.title)) })
        BreedList(navController = navController)
    }
}
