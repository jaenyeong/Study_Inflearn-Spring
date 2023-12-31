package com.jaenyeong.spring02_basic

import com.jaenyeong.spring02_basic.discount.DiscountPolicy
import com.jaenyeong.spring02_basic.discount.RateDiscountPolicy
import com.jaenyeong.spring02_basic.member.MemberRepository
import com.jaenyeong.spring02_basic.member.MemberService
import com.jaenyeong.spring02_basic.member.MemberServiceImpl
import com.jaenyeong.spring02_basic.member.MemoryMemberRepository
import com.jaenyeong.spring02_basic.order.OrderService
import com.jaenyeong.spring02_basic.order.OrderServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

// @Configuration 애너테이션을 태깅하지 않는다면
// 아래 빈들을 생성하는 함수들에 싱글톤이 보장되지 않게 됨
@Configuration
class AppConfig {

    @Bean
    fun memberService(): MemberService {
        return MemberServiceImpl(memberRepository())
    }

    @Bean
    fun memberRepository(): MemberRepository {
        return MemoryMemberRepository()
    }

    @Bean
    fun orderService(): OrderService {
        return OrderServiceImpl(memberRepository = memberRepository(), discountPolicy = discountPolicy())
    }

    @Bean
    fun discountPolicy(): DiscountPolicy {
//        return FixDiscountPolicy()
        return RateDiscountPolicy()
    }
}
