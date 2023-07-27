package com.jaenyeong.spring02_basic.scan

import com.jaenyeong.spring02_basic.AutoAppConfig
import com.jaenyeong.spring02_basic.member.MemberService
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class AutoAppConfigTest : FunSpec({
    context("When ApplicationContext is created") {
        val appContext = AnnotationConfigApplicationContext(AutoAppConfig::class.java)

        test("check component scan") {
            val memberService = appContext.getBean(MemberService::class.java)

            memberService shouldBe instanceOf<MemberService>()
        }
    }
})
