package com.jaenyeong.spring02_basic.order

import com.jaenyeong.spring02_basic.discount.DiscountPolicy
import com.jaenyeong.spring02_basic.discount.FixDiscountPolicy
import com.jaenyeong.spring02_basic.member.MemberRepository
import com.jaenyeong.spring02_basic.member.MemoryMemberRepository
import com.jaenyeong.spring02_basic.product.Product

class OrderServiceImpl(
    private val memberRepository: MemberRepository = MemoryMemberRepository(),
    private val discountPolicy: DiscountPolicy = FixDiscountPolicy()
) : OrderService {

    override fun createOrder(memberId: Long, product: Product): Order {
        val foundMember = memberRepository.findById(memberId)
            ?: throw IllegalStateException("Not exist memberId = $memberId")

        val discountPrice = discountPolicy.discountPrice(grade = foundMember.grade, productPrice = product.price)

        return Order(
            memberId = foundMember.id,
            productName = product.name,
            productPrice = product.price,
            discountPrice = discountPrice
        )
    }
}
