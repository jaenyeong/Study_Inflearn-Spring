package com.jaenyeong.spring02_basic.beandefinition

import com.jaenyeong.spring02_basic.AppConfig
import io.kotest.core.spec.style.FunSpec
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class BeanDefinitionTest : FunSpec({
    context("When ApplicationContext is created") {
        val appContext = AnnotationConfigApplicationContext(AppConfig::class.java)

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
    }
})
