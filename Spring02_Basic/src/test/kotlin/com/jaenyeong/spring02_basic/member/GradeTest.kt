package com.jaenyeong.spring02_basic.member

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class GradeTest : BehaviorSpec({
    Given("If given `Grade` and `productPrice`, `expectedPrice`") {
        val parameterTable = table(
            headers("grade", "productPrice", "expectedPrice"),
            row(Grade.BASIC, 1_000, 0),
            row(Grade.BASIC, 2_000, 0),
            row(Grade.BASIC, 3_000, 0),
            row(Grade.VIP, 4_000, 1000),
            row(Grade.VIP, 6_000, 1000),
            row(Grade.VIP, 10_000, 1000),
        )

        When("Call `discountPrice` function") {
            forAll(parameterTable) { grade, productPrice, expectedPrice ->
                val resultPrice = grade.discountPrice(productPrice)

                Then("Should return the same value as `expectedValue`") {
                    resultPrice shouldBe expectedPrice
                }
            }
        }
    }
})
