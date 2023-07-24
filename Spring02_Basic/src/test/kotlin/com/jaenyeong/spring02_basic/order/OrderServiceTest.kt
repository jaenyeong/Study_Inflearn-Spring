package com.jaenyeong.spring02_basic.order

import com.jaenyeong.spring02_basic.member.Grade.VIP
import com.jaenyeong.spring02_basic.member.Member
import com.jaenyeong.spring02_basic.member.MemberRepository
import com.jaenyeong.spring02_basic.member.MemberService
import com.jaenyeong.spring02_basic.member.MemberServiceImpl
import com.jaenyeong.spring02_basic.member.MemoryMemberRepository
import com.jaenyeong.spring02_basic.product.Product
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class OrderServiceTest : BehaviorSpec({

    Given("If given newMember") {
        val newMember = Member(id = 1L, name = "member1", grade = VIP)
        val memberRepository: MemberRepository = MemoryMemberRepository()
        val memberService: MemberService = MemberServiceImpl(memberRepository = memberRepository)
        memberService.join(newMember)

        When("Creating `order`") {
            val product = Product(name = "Product1", price = 10_000)

            val orderService: OrderService = OrderServiceImpl(memberRepository = memberRepository)
            val createdOrder = orderService.createOrder(newMember.id, product = product)

            Then("`Created Order` is validate") {
                createdOrder.memberId shouldBe newMember.id
                createdOrder.productName shouldBe product.name
                createdOrder.productPrice shouldBe product.price
                createdOrder.discountPrice shouldBe 1_000
            }
        }
    }
})
