package com.jaenyeong.spring02_basic.order

import com.jaenyeong.spring02_basic.product.Product

interface OrderService {
    fun createOrder(memberId: Long, product: Product): Order
}
