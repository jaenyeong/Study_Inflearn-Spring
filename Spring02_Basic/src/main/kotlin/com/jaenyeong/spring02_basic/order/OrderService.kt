package com.jaenyeong.spring02_basic.order

import com.jaenyeong.spring02_basic.product.Product
import org.springframework.stereotype.Service

@Service
interface OrderService {
    fun createOrder(memberId: Long, product: Product): Order
}
