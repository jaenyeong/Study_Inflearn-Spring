package com.jaenyeong.spring02_basic.discount.basic

import com.jaenyeong.spring02_basic.discount.DiscountPolicy

class BasicFixDiscountPolicy : DiscountPolicy {
    private val discountPrice = 0

    override fun discountPrice(productPrice: Int): Int {
        return discountPrice
    }
}
