package com.glima.ilovecats.feature.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.glima.domain.model.Breed
import com.glima.ilovecats.R
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun BreedDetailScreen(
    breed: String,
    viewModel: BreedDetailViewModel = getViewModel(parameters = { parametersOf(breed) })
) {
    Column() {
        TopAppBar(elevation = 4.dp) {
            Text(text = "")
        }

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

}

@Composable
fun BreedDetail(breed: Breed) {
    Column(Modifier.padding(8.dp)) {
        Text(
            text = breed.name,
            fontSize = 18.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.SemiBold,
        )

        Spacer(modifier = Modifier.size(12.dp))

        Text(text = breed.description)

        Spacer(modifier = Modifier.size(12.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            BreedLifeSpan(breed.lifeSpan)
            BreedAffection(breed.affectionLevel)
            BreedIntelligence(breed.intelligence)
        }
    }
}

@Composable
fun BreedIntelligence(intelligence: Int) {
    AttributeIndicator(
        rating = intelligence,
        drawable = R.drawable.ic_kitty_intelligence,
        label = "Intelligence"
    )
}

@Composable
fun BreedAffection(affectionLevel: Int) {
    AttributeIndicator(
        rating = affectionLevel,
        label = "Affection",
        drawable = R.drawable.ic_kitty_affectiveness
    )
}

@Composable
fun BreedLifeSpan(lifeSpan: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_kitty_lifespan),
            contentDescription = null,
        )
        Text(text = "Life Span")
        Text(
            text = "$lifeSpan years",
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun AttributeIndicator(rating: Int, @DrawableRes drawable: Int, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            imageVector = ImageVector.vectorResource(id = drawable),
            contentDescription = null,
        )
        Text(text = label)
        RatingBar(rating)
    }
}

@Composable
fun RatingBar(
    rating: Int = 0,
) {

    val unfilledStars = (5 - rating)

    Row {
        repeat(rating) {
            val iconFilled = ImageVector.vectorResource(R.drawable.ic_paw_filled)
            Image(
                imageVector = iconFilled, contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }

        repeat(unfilledStars) {
            val iconOutlined = ImageVector.vectorResource(R.drawable.ic_paw_outlined)
            Image(
                imageVector = iconOutlined, contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun BreedGallery(viewModel: BreedDetailViewModel) {
    Box(
        modifier = Modifier.height(320.dp)
    ) {
        when (val imageUrls = viewModel.breedGallery.value) {
            is BreedGalleryState.Loaded -> {
                LazyRow {
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
}
