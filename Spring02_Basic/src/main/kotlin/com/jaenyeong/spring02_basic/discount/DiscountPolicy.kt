package com.jaenyeong.spring02_basic.discount

interface DiscountPolicy {
    fun discountPrice(productPrice: Int): Int
}
