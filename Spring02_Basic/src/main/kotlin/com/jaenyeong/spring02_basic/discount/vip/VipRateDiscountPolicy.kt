package com.jaenyeong.spring02_basic.discount.vip

import com.jaenyeong.spring02_basic.discount.DiscountPolicy

class VipRateDiscountPolicy : DiscountPolicy {
    private val discountRate = 10

    override fun discountPrice(productPrice: Int): Int {
        return (productPrice * discountRate) / 100
    }
}
