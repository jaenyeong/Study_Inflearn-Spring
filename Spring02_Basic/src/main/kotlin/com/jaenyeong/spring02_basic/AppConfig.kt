package com.jaenyeong.spring02_basic

import com.jaenyeong.spring02_basic.discount.DiscountPolicy
import com.jaenyeong.spring02_basic.discount.FixDiscountPolicy
import com.jaenyeong.spring02_basic.discount.RateDiscountPolicy
import com.jaenyeong.spring02_basic.member.MemberRepository
import com.jaenyeong.spring02_basic.member.MemberService
import com.jaenyeong.spring02_basic.member.MemberServiceImpl
import com.jaenyeong.spring02_basic.member.MemoryMemberRepository
import com.jaenyeong.spring02_basic.order.OrderService
import com.jaenyeong.spring02_basic.order.OrderServiceImpl

class AppConfig {
    private val memberRepository: MemberRepository = MemoryMemberRepository()

    fun memberService(): MemberService {
        return MemberServiceImpl(memberRepository = memberRepository)
    }

    fun orderService(): OrderService {
        return OrderServiceImpl(memberRepository = memberRepository, discountPolicy = discountPolicy())
    }

    private fun discountPolicy(): DiscountPolicy {
//        return FixDiscountPolicy()
        return RateDiscountPolicy()
    }
}
