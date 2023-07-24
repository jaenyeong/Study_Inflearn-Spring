package com.jaenyeong.spring02_basic.discount

import com.jaenyeong.spring02_basic.member.Grade.BASIC
import com.jaenyeong.spring02_basic.member.Grade.VIP
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class RateDiscountPolicyTest : BehaviorSpec({
    Given("If given `Grade` and `productPrice`, `expectedPrice`") {
        val discountPolicy: DiscountPolicy = RateDiscountPolicy()

        val parameterTable = table(
            headers("grade", "productPrice", "expectedPrice"),
            row(BASIC, 1_000, 0),
            row(BASIC, 2_000, 0),
            row(BASIC, 3_000, 0),
            row(VIP, 4_000, 400),
            row(VIP, 6_000, 600),
            row(VIP, 10_000, 1_000),
        )

        When("Call `discountPrice` function") {
            forAll(parameterTable) { grade, productPrice, expectedPrice ->
                val resultPrice = discountPolicy.discountPrice(grade, productPrice)

                Then("Should return the same value as `expectedValue`") {
                    resultPrice shouldBe expectedPrice
                }
            }
        }
    }
})
