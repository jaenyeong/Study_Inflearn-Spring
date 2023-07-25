package com.jaenyeong.spring02_basic.beanfind

import com.jaenyeong.spring02_basic.AppConfig
import com.jaenyeong.spring02_basic.member.MemberService
import com.jaenyeong.spring02_basic.member.MemberServiceImpl
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ApplicationContextBasicFindTest : FunSpec({
    context("When ApplicationContext is created") {
        val appContext = AnnotationConfigApplicationContext(AppConfig::class.java)

        test("find Bean by name and `Class type`") {
            val memberService = appContext.getBean("memberService", MemberService::class.java)

            memberService shouldBe instanceOf<MemberService>()
        }

        test("find Bean by `Class type` without name") {
            val memberService = appContext.getBean(MemberService::class.java)

            memberService shouldBe instanceOf<MemberService>()
        }

        test("find Bean by name and `Implementation Class type`") {
            val memberServiceImpl = appContext.getBean("memberService", MemberServiceImpl::class.java)
            
            memberServiceImpl shouldBe instanceOf<MemberServiceImpl>()
        }

        test("find `non-existent` Bean by name and `Class Type`") {
            shouldThrow<NoSuchBeanDefinitionException> {
                appContext.getBean("Not exist Bean", MemberService::class.java)
            }
        }
    }
})
