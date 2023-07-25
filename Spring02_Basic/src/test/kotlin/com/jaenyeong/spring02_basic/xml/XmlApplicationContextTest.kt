package com.jaenyeong.spring02_basic.xml

import com.jaenyeong.spring02_basic.member.MemberService
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import org.springframework.context.support.GenericXmlApplicationContext

class XmlApplicationContextTest : FunSpec({
    context("When ApplicationContext is created by XML") {
        val appContext = GenericXmlApplicationContext("AppConfig.xml")

        test("find Bean by name and `Class type`") {
            val memberService = appContext.getBean("memberService", MemberService::class.java)

            memberService shouldBe instanceOf<MemberService>()
        }
    }
})
