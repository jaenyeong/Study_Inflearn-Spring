package com.jaenyeong.spring02_basic.scan.filter

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType.ANNOTATION

class ComponentScanFilterAppConfigTest : FunSpec({
    @Configuration
    @ComponentScan(
        includeFilters = [Filter(type = ANNOTATION, classes = [CustomIncludeBean::class])],
        excludeFilters = [
            Filter(type = ANNOTATION, classes = [CustomExcludeBean::class]),
//            Filter(type = ASSIGNABLE_TYPE, classes = [ExcludeBean::class]),
        ]
    )
    class ComponentScanFilterAppConfig

    context("When ApplicationContext is created") {
        val appContext = AnnotationConfigApplicationContext(ComponentScanFilterAppConfig::class.java)

        test("assert that `custom include annotation` component scan is succeed") {
            val includeBean = appContext.getBean("includeBean", IncludeBean::class.java)

            includeBean shouldBe instanceOf<IncludeBean>()
        }

        test("assert that `custom exclude annotation` component scan is failed") {
            shouldThrow<NoSuchBeanDefinitionException> {
                appContext.getBean("excludeBean", ExcludeBean::class.java)
            }
        }
    }
})
