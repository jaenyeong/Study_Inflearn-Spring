package com.jaenyeong.spring02_basic.autowired.injectionbylistormap

import com.jaenyeong.spring02_basic.autowired.injectionbylistormap.TestGrade.VIP
import org.springframework.stereotype.Component

@Component
class TestFixDiscountPolicy : TestDiscountPolicy {
    private val vipDiscountPrice = 1_000

    override fun discountPrice(grade: TestGrade, productPrice: Int): Int {
        return if (grade == VIP) vipDiscountPrice else 0
    }
}
