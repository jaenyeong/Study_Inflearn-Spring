package com.jaenyeong.spring02_basic

import com.jaenyeong.spring02_basic.member.Grade.VIP
import com.jaenyeong.spring02_basic.member.Member
import com.jaenyeong.spring02_basic.member.MemberService
import com.jaenyeong.spring02_basic.order.OrderService
import com.jaenyeong.spring02_basic.product.Product
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class OrderApp

fun main(args: Array<String>) {
    val context = AnnotationConfigApplicationContext(AppConfig::class.java)

    val memberService = context.getBean("memberService", MemberService::class.java)
    val orderService = context.getBean("orderService", OrderService::class.java)
    val newMember = Member(id = 1L, name = "member1", grade = VIP)
    memberService.join(newMember)

    val createdOrder = orderService.createOrder(newMember.id, product = Product(name = "Product1", price = 30_000))

    println("Created order = $createdOrder")
}
