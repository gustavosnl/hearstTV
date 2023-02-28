package com.glima.ilovecats.feature.detail

import com.glima.data.di.DataModule
import com.glima.domain.model.Breed
import com.glima.ilovecats.di.AppModule
import com.glima.ilovecats.di.PresentationModule
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.koin.KoinExtension
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import org.koin.test.KoinTest
import org.koin.test.inject

class BreedDetailViewModelTest : DescribeSpec(), KoinTest {

    private val breedDetailLogic: BreedDetailLogic by inject()

    private val viewModel: BreedDetailViewModel by inject()

    val breed = Breed(
        id = "cat",
        name = "cat",
        description = "cute cat",
        lifeSpan = "infinite",
        isRare = true,
        affectionLevel = 5,
        intelligence = 5
    )

    init {

        describe("BreedDetail") {
            val breedState = viewModel.breedDetail

            context("#init") {
                it("is loading") {
                    breedState.value.shouldBe(BreedDetailState.Loading)
                }

                context("When it loads successfully") {

                    beforeEach {
                        coEvery {
                            breedDetailLogic.loadBreedDetail(any())
                        } returns BreedDetailState.Loaded(breed)
                    }

                    it("should be Loaded with a breed") {
                        breedState.value.shouldBe(BreedDetailState.Loaded(breed))
                    }
                }

                context("When a error occurs while fetching details") {
                    beforeEach {
                        coEvery {
                            breedDetailLogic.loadBreedDetail(any())
                        } returns BreedDetailState.Error
                    }
                    it("should be Error") {
                        breedState.value.shouldBe(BreedDetailState.Error)
                    }
                }
            }
        }

        describe("BreedGallery") {
            context("#init") {
                val galleryState = viewModel.breedGallery

                it("is loading") {
                    galleryState.value.shouldBe(BreedGalleryState.Loading)
                }

                context("When it loads successfully") {

                    beforeEach {
                        coEvery {
                            breedDetailLogic.loadGallery(any())
                        } returns BreedGalleryState.Loaded(listOf("cat.url"))
                    }

                    it("should be Loaded with a list of urls") {
                        galleryState.value.shouldBe(BreedGalleryState.Loaded(listOf("cat.url")))
                    }
                }

                context("When a error occurs while fetching gallery") {
                    beforeEach {
                        coEvery {
                            breedDetailLogic.loadGallery(any())
                        } returns BreedGalleryState.Error
                    }
                    it("should be Error") {
                        galleryState.value.shouldBe(BreedGalleryState.Error)
                    }
                }
            }
        }
    }

    override fun extensions(): List<Extension> {
        return listOf(
            KoinExtension(
                modules = listOf(
                    AppModule.appModule,
                    PresentationModule.presentationModule,
                    DataModule.dataModule
                )
            )
        )
    }
}
