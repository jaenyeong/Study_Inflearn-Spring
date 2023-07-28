package com.jaenyeong.spring02_basic.beanscope

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope

class BeanScopeTest : FunSpec({
    @Scope("singleton")
    class SingletonBean {
        @PostConstruct
        fun init() {
            println("Singleton Bean : init")
        }

        @PreDestroy
        fun destroy() {
            println("Singleton Bean : destroy")
        }
    }

    @Scope("prototype")
    class PrototypeBean {
        @PostConstruct
        fun init() {
            println("Prototype Bean : init")
        }

        @PreDestroy
        fun destroy() {
            println("Prototype Bean : destroy")
        }
    }

    context("When Bean scope type is different") {
        test("assert that Beans of `singleton` type are same instance") {
            val appContext = AnnotationConfigApplicationContext(SingletonBean::class.java)
            val singletonBean1 = appContext.getBean("beanScopeTest.1.SingletonBean", SingletonBean::class.java)
            val singletonBean2 = appContext.getBean("beanScopeTest.1.SingletonBean", SingletonBean::class.java)

            singletonBean2 shouldBeSameInstanceAs singletonBean1

            // 싱글톤 스코프는 빈 소멸 시 `@PreDestroy` 함수가 호출됨
            appContext.close()
        }

        test("assert that Beans of `prototype` type are different instance") {
            val appContext = AnnotationConfigApplicationContext(PrototypeBean::class.java)
            val prototypeBean1 = appContext.getBean("beanScopeTest.1.PrototypeBean", PrototypeBean::class.java)
            val prototypeBean2 = appContext.getBean("beanScopeTest.1.PrototypeBean", PrototypeBean::class.java)

            prototypeBean2 shouldNotBeSameInstanceAs prototypeBean1

            // 프로토타입 스코프는 빈 소멸 시 `@PreDestroy` 함수가 호출되지 않음
            appContext.close()
        }
    }
})
