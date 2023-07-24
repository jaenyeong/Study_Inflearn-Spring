package com.jaenyeong.spring02_basic.order

import com.jaenyeong.spring02_basic.member.MemberRepository
import com.jaenyeong.spring02_basic.member.MemoryMemberRepository
import com.jaenyeong.spring02_basic.product.Product

class OrderServiceImpl(
    private val memberRepository: MemberRepository = MemoryMemberRepository()
) : OrderService {

    override fun createOrder(memberId: Long, product: Product): Order {
        val foundMember = memberRepository.findById(memberId)
            ?: throw IllegalStateException("Not exist memberId = $memberId")

        return Order(
            memberId = foundMember.id,
            productName = product.name,
            productPrice = product.price,
            discountPrice = foundMember.grade.discountPrice(productPrice = product.price)
        )
    }
}
