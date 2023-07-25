package com.jaenyeong.spring02_basic.beanfind

import com.jaenyeong.spring02_basic.member.MemberRepository
import com.jaenyeong.spring02_basic.member.MemoryMemberRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import org.springframework.beans.factory.NoUniqueBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class ApplicationContextSameBeanFindTest : FunSpec({
    // context 하위에 선언하는 경우 `context` 내부에서 찾을 수 없어 컴파일 에러 발생
    // 람다 표현식 내부에서는 특정 클래스를 참조하려면 클래스가 함수보다 먼저 선언되어 있어야 함
    // 즉 나중에 선언된 클래스를 참조할 수 없음
    @Configuration
    class SameBeanConfig {
        @Bean
        fun memberRepository1(): MemberRepository = MemoryMemberRepository()

        @Bean
        fun memberRepository2(): MemberRepository = MemoryMemberRepository()
    }

    context("When ApplicationContext is created") {
        val appContext = AnnotationConfigApplicationContext(SameBeanConfig::class.java)

        test("find `Non-unique Class type` Beans by `Class type` without name") {
            shouldThrow<NoUniqueBeanDefinitionException> {
                appContext.getBean(MemberRepository::class.java)
            }
        }

        test("find `Non-unique Class type` Beans by name and `Class type`") {
            val memberRepository = appContext.getBean("memberRepository1", MemberRepository::class.java)

            memberRepository shouldBe instanceOf<MemberRepository>()
        }

        test("find all `Non-unique Class type` Beans") {
            val memberRepositoryBeans = appContext.getBeansOfType(MemberRepository::class.java)

            memberRepositoryBeans.forEach { (name, bean) ->
                println("beanName = $name instance = $bean")
            }
        }
    }
})


