package com.glima.data.domain.mapper

import com.glima.data.domain.BreedResponse
import com.glima.domain.model.Breed
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe


internal class BreedMapperTest : DescribeSpec() {

    private val mapper = BreedMapper

    private lateinit var response: BreedResponse

    private lateinit var output: Breed

    init {
        describe("toDomain") {

            response = BreedResponse(
                id = "1",
                name = "Cat",
                description = "cute",
                lifeSpan = "4 ever",
                isRare = 1,
                affectionLevel = 5,
                intelligence = 4
            )

            beforeEach {
                output = mapper.toDomain(response)
            }

            context("#id") {
                it("maps correctly") {
                    output.id.shouldBe(response.id)
                }
            }

            context("#name") {
                it("maps correctly") {
                    output.name.shouldBe(response.name)
                }
            }

            context("#description") {
                it("maps correctly") {
                    output.description.shouldBe(response.description)
                }
            }

            context("#lifeSpan") {
                it("maps correctly") {
                    output.lifeSpan.shouldBe(response.lifeSpan)
                }
            }

            context("#affectionLevel") {
                it("maps correctly") {
                    output.affectionLevel.shouldBe(response.affectionLevel)
                }
            }

            context("#intelligence") {
                it("maps correctly") {
                    output.intelligence.shouldBe(response.intelligence)
                }
            }

            context("#isRare") {
                context("common breed") {
                    response = response.copy(isRare = 0)

                    it("maps to false") {
                        output.isRare.shouldBeFalse()
                    }
                }

                context("rare breed") {
                    response = response.copy(isRare = 1)

                    it("maps to true") {
                        output.isRare.shouldBeTrue()
                    }
                }
            }
        }
    }
}