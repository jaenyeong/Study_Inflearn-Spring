package com.jaenyeong.spring02_basic.autowired.injectionbylistormap

import com.jaenyeong.spring02_basic.autowired.injectionbylistormap.TestGrade.VIP
import org.springframework.stereotype.Component

@Component
class TestRateDiscountPolicy : TestDiscountPolicy {
    private val vipDiscountRate = 10

    override fun discountPrice(grade: TestGrade, productPrice: Int): Int {
        return if (grade == VIP) ((productPrice * vipDiscountRate) / 100) else 0
    }
}
