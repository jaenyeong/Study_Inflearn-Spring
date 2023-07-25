package com.jaenyeong.spring02_basic.beanfind

import com.jaenyeong.spring02_basic.AppConfig
import io.kotest.core.spec.style.FunSpec
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ApplicationContextInfoTest : FunSpec({
    context("When ApplicationContext is created") {
        val appContext = AnnotationConfigApplicationContext(AppConfig::class.java)

        test("find All Bean") {
            appContext.beanDefinitionNames
                .filterNotNull()
                .forEach {
                    println("BeanDefinition name : $it object = ${appContext.getBean(it)}")
                }
        }

        test("find Application Bean") {
            appContext.beanDefinitionNames
                .filterNotNull()
                .filter {
                    // `BeanDefinition.ROLE_APPLICATION` : 직접 등록한 빈
                    // `BeanDefinition.ROLE_INFRASTRUCTURE` : 스프링 내부에서 사용하는 빈
                    appContext.getBeanDefinition(it).role == BeanDefinition.ROLE_APPLICATION
                }
                .forEach {
                    println("BeanDefinition name : $it object = ${appContext.getBean(it)}")
                }
        }
    }
})
