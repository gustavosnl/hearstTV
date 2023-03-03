package com.glima.ilovecats.feature.detail

import androidx.compose.runtime.State
import com.glima.domain.model.Breed
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.test.testCoroutineScheduler
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalStdlibApi::class)
class BreedDetailViewModelSpec : DescribeSpec() {


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

    private lateinit var stateResponse: BreedDetailState
    private lateinit var breedDetailState: State<BreedDetailState>
    private lateinit var galleryState: State<BreedGalleryState>

    init {
        coroutineTestScope = true


        describe("BreedDetail") {
            beforeEach {
                coEvery {
                    breedDetailLogic.loadBreedDetail(any())
                } returns stateResponse
                viewModel.loadBreedInfo()
            }

            context("#init") {
                stateResponse = BreedDetailState.Loading
                testCoroutineScheduler.advanceUntilIdle()

                it("is loading") {
                    breedDetailState.value.shouldBe(BreedDetailState.Loading)
                }

                context("When it loads successfully") {
                    stateResponse = BreedDetailState.Loaded(breed)
                    it("should be Loaded with a breed") {
                        testCoroutineScheduler.advanceUntilIdle()
                        viewModel.breedDetail.value.shouldBe(BreedDetailState.Loaded(breed))
                    }
                }

                context("When a error occurs while fetching details") {
                    stateResponse = BreedDetailState.Error
                    it("should be Error") {
                        testCoroutineScheduler.advanceUntilIdle()
                        breedDetailState.value.shouldBe(BreedDetailState.Error)
                    }
                }
            }
        }

        describe("BreedGallery") {

            context("#init") {
                beforeEach {
                }
                testCoroutineScheduler.advanceUntilIdle()
                galleryState = viewModel.breedGallery

                it("is loading") {
                    galleryState.value.shouldBe(BreedGalleryState.Loading)
                }

                context("When it loads successfully") {

                    coEvery {
                        breedDetailLogic.loadGallery(any())
                    } returns BreedGalleryState.Loaded(listOf("cat.url"))


                    it("should be Loaded with a list of urls") {
                        galleryState.value.shouldBe(BreedGalleryState.Loaded(listOf("cat.url")))
                    }
                }

                context("When a error occurs while fetching gallery") {
                    coEvery {
                        breedDetailLogic.loadGallery(any())
                    } returns BreedGalleryState.Error

                    it("should be Error") {
                        testCoroutineScheduler.runCurrent()
                        galleryState.value.shouldBe(BreedGalleryState.Error)
                    }
                }
            }
        }
    }
}
