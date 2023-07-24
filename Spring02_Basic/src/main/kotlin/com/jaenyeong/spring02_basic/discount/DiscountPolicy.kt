package com.jaenyeong.spring02_basic.discount

import com.jaenyeong.spring02_basic.member.Grade

interface DiscountPolicy {
    fun discountPrice(grade: Grade, productPrice: Int): Int
}
