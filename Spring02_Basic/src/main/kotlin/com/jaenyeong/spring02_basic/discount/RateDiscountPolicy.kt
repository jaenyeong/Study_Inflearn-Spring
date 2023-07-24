package com.jaenyeong.spring02_basic.discount

import com.jaenyeong.spring02_basic.member.Grade
import com.jaenyeong.spring02_basic.member.Grade.VIP

class RateDiscountPolicy: DiscountPolicy {
    private val vipDiscountRate = 10

    override fun discountPrice(grade: Grade, productPrice: Int): Int {
        return if (grade == VIP) ((productPrice * vipDiscountRate) / 100) else 0
    }
}
