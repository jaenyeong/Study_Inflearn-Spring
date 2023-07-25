package com.jaenyeong.spring02_basic.beanfind

import com.jaenyeong.spring02_basic.discount.DiscountPolicy
import com.jaenyeong.spring02_basic.discount.FixDiscountPolicy
import com.jaenyeong.spring02_basic.discount.RateDiscountPolicy
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import org.springframework.beans.factory.NoUniqueBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class ApplicationContextExtendsFindTest : FunSpec({
    @Configuration
    class SameSuperTypeBeanConfig {
        @Bean
        fun fixDiscountPolicy(): DiscountPolicy = FixDiscountPolicy()

        @Bean
        fun rateDiscountPolicy(): DiscountPolicy = RateDiscountPolicy()
    }

    context("When ApplicationContext is created") {
        val appContext = AnnotationConfigApplicationContext(SameSuperTypeBeanConfig::class.java)

        test("find `Non-unique Class type` Beans by `Super type` without name") {
            shouldThrow<NoUniqueBeanDefinitionException> {
                appContext.getBean(DiscountPolicy::class.java)
            }
        }

        test("find `Non-unique Class type` Beans by `Concrete type`") {
            val fixDiscountPolicy = appContext.getBean(FixDiscountPolicy::class.java)

            fixDiscountPolicy shouldBe instanceOf<DiscountPolicy>()
            fixDiscountPolicy shouldBe instanceOf<FixDiscountPolicy>()
        }

        test("find `Non-unique Class type` Beans by name and `Super type`") {
            val fixDiscountPolicy = appContext.getBean("fixDiscountPolicy", DiscountPolicy::class.java)

            fixDiscountPolicy shouldBe instanceOf<DiscountPolicy>()
            fixDiscountPolicy shouldBe instanceOf<FixDiscountPolicy>()
        }

        test("find all `Non-unique Class type` Beans") {
            val discountPolicyBeans = appContext.getBeansOfType(DiscountPolicy::class.java)

            discountPolicyBeans.forEach { (name, bean) ->
                println("beanName = $name instance = $bean")
            }
        }

        // 코틀린에서는 모든 객체가 `Any`의 하위 타입이기 때문에 `Object::class.java`로 하면 모든 빈을 가져올 수 없음
        test("find all Beans by `Any type`") {
            val allBeans = appContext.getBeansOfType(Any::class.java)

            allBeans.forEach { (name, bean) ->
                println("beanName = $name instance = $bean")
            }
        }
    }
})
