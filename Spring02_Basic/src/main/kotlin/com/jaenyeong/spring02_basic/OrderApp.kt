package com.jaenyeong.spring02_basic

import com.jaenyeong.spring02_basic.member.Grade.VIP
import com.jaenyeong.spring02_basic.member.Member
import com.jaenyeong.spring02_basic.member.MemberService
import com.jaenyeong.spring02_basic.member.MemberServiceImpl
import com.jaenyeong.spring02_basic.member.MemoryMemberRepository
import com.jaenyeong.spring02_basic.order.OrderService
import com.jaenyeong.spring02_basic.order.OrderServiceImpl
import com.jaenyeong.spring02_basic.product.Product

class OrderApp

fun main(args: Array<String>) {
    val memberRepository = MemoryMemberRepository()
    val memberService: MemberService = MemberServiceImpl(memberRepository = memberRepository)
    val orderService: OrderService = OrderServiceImpl(memberRepository = memberRepository)
    val newMember = Member(id = 1L, name = "member1", grade = VIP)
    memberService.join(newMember)

    val createdOrder = orderService.createOrder(newMember.id, product = Product(name = "Product1", price = 10_000))

    println("Created order = $createdOrder")
}
