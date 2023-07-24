package com.jaenyeong.spring02_basic.order

class Order(
    val memberId: Long,
    val productName: String,
    val productPrice: Int,
    val discountPrice: Int
) {
    fun calculateFinalPrice(): Int {
        return productPrice - discountPrice
    }

    override fun toString(): String {
        return "Order(memberId=$memberId, productName='$productName', productPrice=$productPrice, discountPrice=$discountPrice)"
    }
}
