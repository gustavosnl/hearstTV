package com.glima.ilovecats.feature.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.glima.domain.model.Breed
import com.glima.ilovecats.R
import com.glima.ilovecats.Screen
import com.glima.ilovecats.feature.list.BreedIcon
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun BreedDetailScreen(
    breed: String,
    navController: NavHostController,
    viewModel: BreedDetailViewModel = getViewModel(parameters = { parametersOf(breed) })
) {
    Column {
        BreedDetailTopAppBar(navController)
        BreedGallery(viewModel)
        when (val breedDetailState = viewModel.breedDetail.value) {
            is BreedDetailState.Loaded -> {
                BreedDetail(breedDetailState.breed)
            }
            is BreedDetailState.Loading -> {
                LinearProgressIndicator(Modifier.fillMaxWidth())
            }
            is BreedDetailState.Error -> {
                Text(text = stringResource(id = R.string.load_error))
            }
        }
    }
}

@Composable
private fun BreedDetailTopAppBar(navController: NavHostController) {
    TopAppBar(
        title = { Text(text = stringResource(id = Screen.BreedDetail.title)) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.breed_detail_nav_back)
                )
            }
        })
}

@Composable
fun BreedDetail(breed: Breed) {
    Column(Modifier.padding(8.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            BreedIcon(breed)
            Text(
                text = breed.name,
                fontSize = 18.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
            )
        }

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
        label = stringResource(id = R.string.intelligence_attribute_label)
    )
}

@Composable
fun BreedAffection(affectionLevel: Int) {
    AttributeIndicator(
        rating = affectionLevel,
        label = stringResource(id = R.string.affection_attribute_label),
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
        Text(text = stringResource(id = R.string.lifespan_attribute_label))
        Text(
            text = stringResource(id = R.string.lifespan_attribute_value, lifeSpan),
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
    val MAX_RATE = 5
    val unfilledStars = (MAX_RATE - rating)

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
                        SubcomposeAsyncImage(
                            model = it,
                            loading = {
                                Image(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_image_placeholder),
                                    contentDescription = null
                                )
                            },
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(320.dp)
                                .aspectRatio(1f, true)

                        )
                    }
                }
            }
            is BreedGalleryState.Loading -> {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is BreedGalleryState.Error -> {
                Column() {
                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_img_error_placeholder),
                        contentDescription = null
                    )
                    Text(text = stringResource(id = R.string.load_error))
                }
            }
        }
    }
}
