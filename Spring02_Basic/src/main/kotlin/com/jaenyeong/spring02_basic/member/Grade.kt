package com.jaenyeong.spring02_basic.member

import com.jaenyeong.spring02_basic.discount.DiscountPolicy
import com.jaenyeong.spring02_basic.discount.basic.BasicFixDiscountPolicy
import com.jaenyeong.spring02_basic.discount.vip.VipFixDiscountPolicy

enum class Grade(private val discountPolicy: DiscountPolicy) {
    BASIC (discountPolicy = BasicFixDiscountPolicy()),
    VIP (discountPolicy = VipFixDiscountPolicy());

    fun discountPrice(productPrice: Int): Int {
        return discountPolicy.discountPrice(productPrice = productPrice)
    }
}
