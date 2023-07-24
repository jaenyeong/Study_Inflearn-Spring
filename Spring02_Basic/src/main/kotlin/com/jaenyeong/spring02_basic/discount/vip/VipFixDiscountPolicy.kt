package com.jaenyeong.spring02_basic.discount.vip

import com.jaenyeong.spring02_basic.discount.DiscountPolicy

class VipFixDiscountPolicy : DiscountPolicy {
    private val discountPrice = 1_000

    override fun discountPrice(productPrice: Int): Int {
        return discountPrice
    }
}
