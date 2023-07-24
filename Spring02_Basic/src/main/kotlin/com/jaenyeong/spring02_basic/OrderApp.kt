package com.jaenyeong.spring02_basic

import com.jaenyeong.spring02_basic.member.Grade.VIP
import com.jaenyeong.spring02_basic.member.Member
import com.jaenyeong.spring02_basic.product.Product

class OrderApp

fun main(args: Array<String>) {
    val config = AppConfig()
    val memberService = config.memberService()
    val orderService = config.orderService()
    val newMember = Member(id = 1L, name = "member1", grade = VIP)
    memberService.join(newMember)

    val createdOrder = orderService.createOrder(newMember.id, product = Product(name = "Product1", price = 30_000))

    println("Created order = $createdOrder")
}
