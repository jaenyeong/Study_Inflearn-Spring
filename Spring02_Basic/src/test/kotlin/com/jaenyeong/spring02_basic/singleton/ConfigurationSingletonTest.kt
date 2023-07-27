package com.jaenyeong.spring02_basic.singleton

import com.jaenyeong.spring02_basic.AppConfig
import com.jaenyeong.spring02_basic.member.MemberRepository
import com.jaenyeong.spring02_basic.member.MemberServiceImpl
import com.jaenyeong.spring02_basic.order.OrderServiceImpl
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ConfigurationSingletonTest : FunSpec({
    context("When the Bean is automatically injected by the `Spring`") {
        val appContext = AnnotationConfigApplicationContext(AppConfig::class.java)
        val memberRepository = appContext.getBean("memberRepository", MemberRepository::class.java)
        val memberService = appContext.getBean("memberService", MemberServiceImpl::class.java)
        val orderService = appContext.getBean("orderService", OrderServiceImpl::class.java)

        test("assert `Spring Bean` is singleton") {
            val memberServiceMemberRepository = memberService.memberRepository
            val orderServiceMemberRepository = orderService.memberRepository

            memberServiceMemberRepository shouldBeSameInstanceAs memberRepository
            orderServiceMemberRepository shouldBeSameInstanceAs memberRepository
            orderServiceMemberRepository shouldBeSameInstanceAs memberServiceMemberRepository
        }
    }
})
