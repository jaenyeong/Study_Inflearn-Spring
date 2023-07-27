package com.jaenyeong.spring02_basic.singleton

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class StatelessProductTest : FunSpec({
    @Configuration
    class StatelessConfig {
        @Bean
        fun statelessProduct(): StatelessProduct {
            return StatelessProduct()
        }
    }

    context("When ApplicationContext is created") {
        val appContext = AnnotationConfigApplicationContext(StatelessConfig::class.java)
        val statelessProduct1 = appContext.getBean(StatelessProduct::class.java)
        val statelessProduct2 = appContext.getBean(StatelessProduct::class.java)

        test("use `StatelessProduct` Bean") {
            statelessProduct2 shouldBeSameInstanceAs statelessProduct1

            val statelessPrice1 = statelessProduct1.price(name = "User1", price = 10_000)
            val statelessPrice2 = statelessProduct1.price(name = "User2", price = 20_000)

            statelessPrice2 shouldNotBe statelessPrice1
        }
    }
})
