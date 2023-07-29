package com.jaenyeong.spring02_basic.beanscope

import com.jaenyeong.spring02_basic.beanscope.client.CountClient
import com.jaenyeong.spring02_basic.beanscope.counter.SingletonCounter
import com.jaenyeong.spring02_basic.beanscope.counter.PrototypeCounter
import com.jaenyeong.spring02_basic.beanscope.client.ProviderCountClient
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class SingletonWithPrototypeTest : FunSpec({
    context("When given `Singleton Counter` Bean") {
        val appContext = AnnotationConfigApplicationContext(SingletonCounter::class.java)
        val counter1 = appContext.getBean("singletonCounter", SingletonCounter::class.java)
        val counter2 = appContext.getBean("singletonCounter", SingletonCounter::class.java)

        test("assert that `Singleton Counter` Beans`s values are each same") {
            counter1.increase()
            counter1.increase()
            counter2.increase()

            val value1 = counter1.value
            val value2 = counter2.value

            value1 shouldBe 3
            value2 shouldBe 3
            value2 shouldBe value1

            appContext.close()
        }
    }

    context("When given `Prototype Counter` Bean") {
        val appContext = AnnotationConfigApplicationContext(PrototypeCounter::class.java)
        val counter1 = appContext.getBean("prototypeCounter", PrototypeCounter::class.java)
        val counter2 = appContext.getBean("prototypeCounter", PrototypeCounter::class.java)

        test("assert that `Prototype Counter` Beans`s values are each different") {
            counter1.increase()
            counter1.increase()
            counter2.increase()

            val value1 = counter1.value
            val value2 = counter2.value

            value1 shouldBe 2
            value2 shouldBe 1
            value2 shouldNotBe value1

            appContext.close()
        }
    }

    context("When given singleton `CountClient` Bean") {
        val appContext = AnnotationConfigApplicationContext(CountClient::class.java, PrototypeCounter::class.java)
        val client1 = appContext.getBean("countClient", CountClient::class.java)
        val client2 = appContext.getBean("countClient", CountClient::class.java)

        test("assert that `Prototype Counter` Bean used inside `Client` is initialized only once") {
            client1.increase()
            client1.increase()
            client2.increase()

            val value1 = client1.value
            val value2 = client2.value

            value1 shouldBe 3
            value2 shouldBe 3
            value2 shouldBe value1

            appContext.close()
        }
    }

    context("When given singleton `ProviderCountClient` Bean") {
        val appContext = AnnotationConfigApplicationContext(ProviderCountClient::class.java, PrototypeCounter::class.java)
        val client1 = appContext.getBean("providerCountClient", ProviderCountClient::class.java)
        val client2 = appContext.getBean("providerCountClient", ProviderCountClient::class.java)

        test("assert that `Prototype Counter` Bean used inside `Client` is initialized each time it is used") {
            val providerCounter = client1.providerCounter
            val objectProviderCounter = client1.objectProviderCounter
            val objectFactoryCounter = client1.objectFactoryCounter

            providerCounter shouldNotBeSameInstanceAs objectProviderCounter
            objectProviderCounter shouldNotBeSameInstanceAs objectFactoryCounter
            objectFactoryCounter shouldNotBeSameInstanceAs providerCounter
        }

        test("assert that `ProviderCountClient` Bean uses a new `Counter` instance each time it is used") {
            val execCounter1 = client1.increaseAndGetCounter()
            val execCounter2 = client1.increaseAndGetCounter()
            val execCounter3 = client2.increaseAndGetCounter()

            val value1 = client1.value
            val value2 = client2.value

            value1 shouldBe 1
            value2 shouldBe 1

            execCounter1 shouldNotBeSameInstanceAs execCounter2
            execCounter2 shouldNotBeSameInstanceAs execCounter3
            execCounter3 shouldNotBeSameInstanceAs execCounter1
        }
    }
})
