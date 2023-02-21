package com.glima.ilovecats.feature.detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun BreedDetail(
    breed: String,
    viewModel: BreedDetailViewModel = getViewModel(parameters = { parametersOf(breed) })
) {
    Text(text = breed)
}

