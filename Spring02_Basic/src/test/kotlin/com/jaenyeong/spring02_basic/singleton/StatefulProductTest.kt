package com.jaenyeong.spring02_basic.singleton

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class StatefulProductTest : FunSpec({
    @Configuration
    class StatefulConfig {
        @Bean
        fun statefulProduct(): StatefulProduct {
            return StatefulProduct()
        }
    }

    context("When ApplicationContext is created") {
        val appContext = AnnotationConfigApplicationContext(StatefulConfig::class.java)
        val statefulProduct1 = appContext.getBean(StatefulProduct::class.java)
        val statefulProduct2 = appContext.getBean(StatefulProduct::class.java)

        test("use `StatefulProduct` Bean") {
            statefulProduct2 shouldBeSameInstanceAs statefulProduct1

            statefulProduct1.price(name = "User1", price = 10_000)
            statefulProduct1.price shouldBe 10_000

            statefulProduct2.price(name = "User2", price = 20_000)
            statefulProduct1.price shouldNotBe 10_000
        }
    }
})
