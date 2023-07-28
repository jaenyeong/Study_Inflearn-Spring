package com.jaenyeong.spring02_basic.autowired.injectionbylistormap

interface TestDiscountPolicy {
    fun discountPrice(grade: TestGrade, productPrice: Int): Int
}
