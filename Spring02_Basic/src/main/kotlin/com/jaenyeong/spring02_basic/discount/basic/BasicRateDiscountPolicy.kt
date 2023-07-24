package com.jaenyeong.spring02_basic.discount.basic

import com.jaenyeong.spring02_basic.discount.DiscountPolicy

class BasicRateDiscountPolicy: DiscountPolicy {
    private val discountRate = 0

    override fun discountPrice(productPrice: Int): Int {
        return discountRate
    }
}
