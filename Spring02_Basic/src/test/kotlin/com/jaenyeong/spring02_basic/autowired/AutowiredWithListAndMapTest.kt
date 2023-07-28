package com.jaenyeong.spring02_basic.autowired

import com.jaenyeong.spring02_basic.autowired.injectionbylistormap.TestDiscountService
import com.jaenyeong.spring02_basic.autowired.injectionbylistormap.TestDiscountPolicy
import com.jaenyeong.spring02_basic.autowired.injectionbylistormap.TestGrade.VIP
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

class AutowiredWithListAndMapTest : FunSpec({
    @Configuration
    @ComponentScan(
        basePackages = ["com.jaenyeong.spring02_basic.autowired.injectionbylistormap"]
    )
    class TestListMapAutowiredConfig

    context("When ApplicationContext is created") {
        val appContext = AnnotationConfigApplicationContext(TestListMapAutowiredConfig::class.java)
        val testDiscountService = appContext.getBean("testDiscountService", TestDiscountService::class.java)

        test("assert dependency injection can also be received with `List`, `Map`") {
            testDiscountService.policies shouldNotBe emptyList<TestDiscountPolicy>()
            testDiscountService.policyMap shouldNotBe emptyMap<String, TestDiscountPolicy>()
        }

        test("when `testFixDiscountPolicy`") {
            val discountPrice = testDiscountService.discount(grade = VIP, productPrice = 20_000, discountCode = "testFixDiscountPolicy")

            discountPrice shouldBe 1_000
        }

        test("when `testRateDiscountPolicy`") {
            val discountPrice = testDiscountService.discount(grade = VIP, productPrice = 30_000, discountCode = "testRateDiscountPolicy")

            discountPrice shouldBe 3_000
        }
    }
})
