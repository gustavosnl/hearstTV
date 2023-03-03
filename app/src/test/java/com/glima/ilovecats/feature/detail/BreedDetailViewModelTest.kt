package com.glima.ilovecats.feature.detail

import androidx.compose.runtime.State
import com.glima.domain.model.Breed
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class BreedDetailViewModelTest {
    private val breed = Breed(
        id = "cat",
        name = "cat",
        description = "cute cat",
        lifeSpan = "infinite",
        isRare = true,
        affectionLevel = 5,
        intelligence = 5
    )

    private val breedDetailLogic: BreedDetailLogic = mockk()

    private val viewModel: BreedDetailViewModel by lazy {
        BreedDetailViewModel(Dispatchers.Unconfined, breed.id, breedDetailLogic)
    }

    @Nested
    @DisplayName("BreedDetail")
    inner class BreedDetail {
        private lateinit var breedDetailState: State<BreedDetailState>

        @Nested
        @DisplayName("#init")
        inner class Init {

            @BeforeEach
            fun setup() {
                coEvery {
                    breedDetailLogic.loadBreedDetail(any())
                } returns BreedDetailState.Loading

                viewModel.loadBreedInfo()
                breedDetailState = viewModel.breedDetail
            }

            @Test
            @DisplayName("initial state is loading")
            fun isLoading() = runTest {
                breedDetailState.value.shouldBe(BreedDetailState.Loading)
            }
        }

        @Nested
        @DisplayName("When it loads successfully")
        inner class Loaded {
            @BeforeEach
            fun setup() {
                coEvery {
                    breedDetailLogic.loadBreedDetail(any())
                } returns BreedDetailState.Loaded(breed)

                viewModel.loadBreedInfo()
                breedDetailState = viewModel.breedDetail
            }

            @Test
            @DisplayName("the state is loaded")
            fun isLoaded() = runTest {
                breedDetailState.value.shouldBeEqualToComparingFields(BreedDetailState.Loaded(breed))
            }
        }

        @Nested
        @DisplayName("When a error occurs while fetching details")
        inner class Error {

            @BeforeEach
            fun setup() {
                coEvery {
                    breedDetailLogic.loadBreedDetail(any())
                } returns BreedDetailState.Error

                viewModel.loadBreedInfo()
                breedDetailState = viewModel.breedDetail
            }


            @Test
            @DisplayName("the state is error")
            fun isError() = runTest {
                breedDetailState.value.shouldBe(BreedDetailState.Error)
            }
        }
    }

    @Nested
    @DisplayName("BreedGallery")
    inner class BreedGallery {
        private lateinit var galleryState: State<BreedGalleryState>

        @Nested
        @DisplayName("#init")
        inner class Init {

            @BeforeEach
            fun setup() {
                coEvery {
                    breedDetailLogic.loadGallery(any())
                } returns BreedGalleryState.Loading

                viewModel.loadBreedGallery()
                galleryState = viewModel.breedGallery
            }

            @Test
            @DisplayName("initial state is loading")
            fun isLoading() = runTest {
                galleryState.value.shouldBe(BreedGalleryState.Loading)
            }
        }

        @Nested
        @DisplayName("When it loads successfully")
        inner class Loaded {
            private val urlsToLoad = listOf("url.cat.com")


            @BeforeEach
            fun setup() {
                coEvery {
                    breedDetailLogic.loadGallery(any())
                } returns BreedGalleryState.Loaded(urlsToLoad)

                viewModel.loadBreedGallery()
                galleryState = viewModel.breedGallery
            }

            @Test
            @DisplayName("the state is loaded")
            fun isLoaded() = runTest {
                galleryState.value.shouldBeEqualToComparingFields(BreedGalleryState.Loaded(urlsToLoad))
            }
        }

        @Nested
        @DisplayName("When a error occurs while fetching gallery")
        inner class Error {

            @BeforeEach
            fun setup() {
                coEvery {
                    breedDetailLogic.loadGallery(any())
                } returns BreedGalleryState.Error

                viewModel.loadBreedGallery()
                galleryState = viewModel.breedGallery
            }

            @Test
            @DisplayName("the state is error")
            fun isError() = runTest {
                galleryState.value.shouldBe(BreedGalleryState.Error)
            }
        }
    }
}
