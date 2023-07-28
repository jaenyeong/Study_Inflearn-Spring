package com.jaenyeong.spring02_basic.order

import com.jaenyeong.spring02_basic.annotation.MainDiscountPolicy
import com.jaenyeong.spring02_basic.discount.DiscountPolicy
import com.jaenyeong.spring02_basic.member.MemberRepository
import com.jaenyeong.spring02_basic.product.Product
import org.springframework.stereotype.Component

@Component
class OrderServiceImpl(
    val memberRepository: MemberRepository,
    @MainDiscountPolicy private val discountPolicy: DiscountPolicy
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
