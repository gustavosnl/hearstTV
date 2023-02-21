package com.glima.ilovecats.feature.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.glima.domain.model.Breed
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun BreedDetailScreen(
    breed: String,
    viewModel: BreedDetailViewModel = getViewModel(parameters = { parametersOf(breed) })
) {

    when (val breedDetailState = viewModel.breedDetail.value) {
        is BreedDetailState.Loaded -> {
            Column() {
                BreedGallery(viewModel)
                BreedDetail(breedDetailState.breed)
            }
        }
        is BreedDetailState.Loading -> {
            Text(text = "Carregando")
        }
    }
}

@Composable
fun BreedDetail(breed: Breed) {
    Column() {
        Text(text = breed.name)
        Text(text = breed.description)
        Row() {
            BreedLifeSpan(breed.lifeSpan)
            BreedAffection(breed.affectionLevel)
            BreedIntelligence(breed.intelligence)
        }
    }
}

@Composable
fun BreedIntelligence(intelligence: Int) {
    Text(text = "Intelligence : $intelligence")
}

@Composable
fun BreedAffection(affectionLevel: Int) {
    Text(text = "Affection : $affectionLevel")
}

@Composable
fun BreedLifeSpan(lifeSpan: String) {
    Text(text = "Life Span : $lifeSpan")
}

@Composable
fun BreedGallery(viewModel: BreedDetailViewModel) {
    when (val imageUrls = viewModel.breedGallery.value) {
        is BreedGalleryState.Loaded -> {
            LazyRow(
            ) {
                items(imageUrls.imageUrls) {
                    AsyncImage(
                        model = it,
                        contentDescription = "A picture of a cat",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(320.dp)
                            .aspectRatio(1f, true)
                    )
                }
            }
        }
    }
}


