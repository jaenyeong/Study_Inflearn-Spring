package com.jaenyeong.spring02_basic.autowired

import com.jaenyeong.spring02_basic.autowired.sametypebean.SameTypeBean
import com.jaenyeong.spring02_basic.autowired.sametypebean.SubBeanA
import com.jaenyeong.spring02_basic.autowired.sametypebean.SubBeanB
import com.jaenyeong.spring02_basic.autowired.sametypebean.SubNotPrimaryBean
import com.jaenyeong.spring02_basic.autowired.sametypebean.SubPrimaryBean
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

class AutowiredSameTypeBeanTest : FunSpec({
    @Configuration
    @ComponentScan("com.jaenyeong.spring02_basic.autowired.sametypebean")
    class DuplicationBeanConfig

    context("When ApplicationContext is created") {
        val appContext = AnnotationConfigApplicationContext(DuplicationBeanConfig::class.java)

        test("assert dependencies injection when there are multiple Beans of the same type") {
            val sameTypeBean = appContext.getBean("sameTypeBean", SameTypeBean::class.java)

            sameTypeBean.subNotPrimaryBean shouldBe instanceOf<SubNotPrimaryBean>()
            sameTypeBean.superPrimaryBean shouldBe instanceOf<SubPrimaryBean>()
            sameTypeBean.subBeanA shouldBe instanceOf<SubBeanA>()
            sameTypeBean.superBean shouldBe instanceOf<SubBeanB>()
        }
    }
})
