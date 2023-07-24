package com.jaenyeong.spring02_basic.discount

import com.jaenyeong.spring02_basic.member.Grade
import com.jaenyeong.spring02_basic.member.Grade.VIP

class FixDiscountPolicy: DiscountPolicy {
    private val vipDiscountPrice = 1_000

    override fun discountPrice(grade: Grade, productPrice: Int): Int {
        return if (grade == VIP) vipDiscountPrice else 0
    }
}
