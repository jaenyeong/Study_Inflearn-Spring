package com.jaenyeong.spring02_basic.autowired

import com.jaenyeong.spring02_basic.autowired.injectionway.AutowiredBean
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

class AutowiredTest : FunSpec({
    @Configuration
    @ComponentScan("com.jaenyeong.spring02_basic.autowired.injectionway")
    class AutowiredConfig

    context("When AutowiredConfig ApplicationContext is created") {
        val appContext = AnnotationConfigApplicationContext(AutowiredConfig::class.java)

        test("find application Bean and check the metadata") {
            appContext.beanDefinitionNames
                .filterNotNull()
                .filter {
                    appContext.getBeanDefinition(it).role == BeanDefinition.ROLE_APPLICATION
                }
                .forEach {
                    println("[BeanName = ${it}] BeanDefinition = ${appContext.getBeanDefinition(it)}")
                }
        }

        test("assert that dependencies auto injection of `Spring Bean`") {
            val autowiredBean = appContext.getBean("autowiredBean", AutowiredBean::class.java)

            autowiredBean.constructorInjection shouldNotBe null
            autowiredBean.fieldInjection shouldNotBe null
            autowiredBean.functionInjection shouldNotBe null
            autowiredBean.constructorInjection shouldNotBe null
            autowiredBean.providerInjection shouldNotBe null
        }
    }
})
