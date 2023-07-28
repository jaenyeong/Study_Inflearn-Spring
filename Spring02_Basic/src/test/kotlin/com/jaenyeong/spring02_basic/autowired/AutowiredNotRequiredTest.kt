package com.jaenyeong.spring02_basic.autowired

import com.jaenyeong.spring02_basic.autowired.notrequired.NotRequiredComponent
import io.kotest.core.spec.style.FunSpec
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

class AutowiredNotRequiredTest : FunSpec({
    @Configuration
    @ComponentScan(basePackages = ["com.jaenyeong.spring02_basic.autowired.notrequired"])
    class AutowiredNotRequiredConfig

    context("When AutowiredNotRequiredBean ApplicationContext is created") {
        val appContext = AnnotationConfigApplicationContext(AutowiredNotRequiredConfig::class.java)

        test("assert that `un required option` Bean is injected") {
            appContext.getBean("notRequiredComponent", NotRequiredComponent::class.java)
        }
    }
})
