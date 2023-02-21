package com.glima.ilovecats.feature.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
            BreedDetail(breedDetailState.breed)
        }
        is BreedDetailState.Loading -> {
            Text(text = "Carregando")
        }
    }
}

@Composable
fun BreedDetail(breed: Breed) {
    Column() {
        BreedGallery(breed)
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
fun BreedGallery(breed: Breed) {
    Text(text = "${breed.name} Gallery")
}


