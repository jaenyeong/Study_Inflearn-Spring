package com.jaenyeong.spring02_basic.beanlifecycle

import io.kotest.core.spec.style.FunSpec
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class BeanLifeCycleTest : FunSpec({
    @Configuration
    class LifeCycleConfig1 {
        @Bean
        fun networkClient1(): NetworkClient1 {
            return NetworkClient1("http://localhost:8080")
        }
    }

    @Configuration
    class LifeCycleConfig2 {
        // `destroyMethod`는 기본값이 `AbstractBeanDefinition.INFER_METHOD`(inferred)
        // 이는 추론을 의미하며 `close`, `shutdown` 등의 함수를 소멸자(종료) 함수로 추정하여 자동으로 호출해줌
        // 이를 피하려면 `destroyMethod = ""` 처럼 공백으로 지정할 것
        @Bean(initMethod = "init", destroyMethod = "release")
        fun networkClient2(): NetworkClient2 {
            return NetworkClient2("http://localhost:8080")
        }
    }

    @Configuration
    class LifeCycleConfig3 {
        @Bean
        fun networkClient3(): NetworkClient3 {
            return NetworkClient3("http://localhost:8080")
        }
    }

    context("When ApplicationContext is created") {
        test("check Bean`s lifecycle that implements `InitializingBean`, `DisposableBean`") {
            val appContext = AnnotationConfigApplicationContext(LifeCycleConfig1::class.java)
            appContext.getBean(NetworkClient1::class.java)
            appContext.close()
        }

        test("check Bean`s lifecycle that set `initMethod`, `destroyMethod`") {
            val appContext = AnnotationConfigApplicationContext(LifeCycleConfig2::class.java)
            appContext.getBean(NetworkClient2::class.java)
            appContext.close()
        }

        test("check Bean`s lifecycle that use `@PostConstruct`, `@PreDestroy`") {
            val appContext = AnnotationConfigApplicationContext(LifeCycleConfig3::class.java)
            appContext.getBean(NetworkClient3::class.java)
            appContext.close()
        }
    }
})
