package com.jaenyeong.spring02_basic.discount

import com.jaenyeong.spring02_basic.annotation.MainDiscountPolicy
import com.jaenyeong.spring02_basic.member.Grade
import com.jaenyeong.spring02_basic.member.Grade.VIP
import org.springframework.stereotype.Component

@Component
@MainDiscountPolicy
class RateDiscountPolicy: DiscountPolicy {
    private val vipDiscountRate = 10

    override fun discountPrice(grade: Grade, productPrice: Int): Int {
        return if (grade == VIP) ((productPrice * vipDiscountRate) / 100) else 0
    }
}
