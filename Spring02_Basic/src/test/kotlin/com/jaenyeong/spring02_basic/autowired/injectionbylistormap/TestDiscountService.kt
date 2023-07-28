package com.jaenyeong.spring02_basic.autowired.injectionbylistormap

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException

@Component
class TestDiscountService(
    @Autowired val policies: List<TestDiscountPolicy>,
    @Autowired val policyMap: Map<String, TestDiscountPolicy>
) {
    init {
        println("Injected testDiscountPolicies : $policies")
        println("Injected testDiscountPolicyMap : $policyMap")
    }

    fun discount(grade: TestGrade, productPrice: Int, discountCode: String): Int {
        val targetPolicy = policyMap[discountCode]
        return targetPolicy?.discountPrice(grade = grade, productPrice = productPrice)
            ?: throw IllegalArgumentException("Invalid parameters")
    }
}
