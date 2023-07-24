package com.jaenyeong.spring02_basic.discount.vip

import com.jaenyeong.spring02_basic.discount.DiscountPolicy
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class VipRateDiscountPolicyTest : BehaviorSpec({
    Given("If given `productPrice`, `expectedPrice`") {
        val discountPolicy: DiscountPolicy = VipRateDiscountPolicy()
        val parameterTable = table(
            headers("productPrice", "expectedPrice"),
            row(1_000, 100),
            row(2_000, 200),
            row(4_000, 400),
            row(6_000, 600),
            row(10_000, 1000),
        )

        When("Call `VIPRateDiscountPolicy` class `discountPrice` function") {
            forAll(parameterTable) { productPrice, expectedPrice ->
                val resultPrice = discountPolicy.discountPrice(productPrice)

                Then("Should return the same value as `(productPrice * discountRate) / 100`") {
                    resultPrice shouldBe expectedPrice
                }
            }
        }
    }

    Given("If given `productPrice`") {
        val discountPolicy: DiscountPolicy = VipRateDiscountPolicy()
        val parameterTable = table(
            headers("productPrice"),
            row(120_000),
            row(2_222),
            row(777_777),
            row(1_000_000),
            row(999_123),
        )

        When("Call `VIPRateDiscountPolicy` class `discountPrice` function") {
            forAll(parameterTable) { productPrice ->
                val resultPrice = discountPolicy.discountPrice(productPrice)

                Then("Should return the same value as `(productPrice * discountRate) / 100`") {
                    val expectedPrice = (productPrice * 10) / 100

                    resultPrice shouldBe expectedPrice
                }
            }
        }
    }
})
