package com.jaenyeong.spring02_basic.singleton

import com.jaenyeong.spring02_basic.AppConfig
import com.jaenyeong.spring02_basic.member.MemberService
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class SingletonTest : FunSpec({
    context("When using a non-singleton") {
        val appConfig = AppConfig()

        test("create an instance each time a request in received") {
            val memberService1 = appConfig.memberService()
            val memberService2 = appConfig.memberService()

            memberService2 shouldNotBeSameInstanceAs memberService1
        }
    }

    context("When using a pure singleton") {
        test("use SingletonService") {
            val singletonService1 = SingletonService
            val singletonService2 = SingletonService

            singletonService2 shouldBeSameInstanceAs singletonService1
        }
    }

    context("When using a spring singleton") {
        val appConfig = AnnotationConfigApplicationContext(AppConfig::class.java)

        test("use a single scope Bean") {
            val memberService1 = appConfig.getBean("memberService", MemberService::class.java)
            val memberService2 = appConfig.getBean("memberService", MemberService::class.java)

            memberService2 shouldBeSameInstanceAs memberService1
        }
    }
})
