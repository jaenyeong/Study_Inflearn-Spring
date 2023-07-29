package com.jaenyeong.spring02_basic.beanscope.client

import com.jaenyeong.spring02_basic.beanscope.counter.PrototypeCounter
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import jakarta.inject.Provider
import org.springframework.beans.factory.ObjectFactory
import org.springframework.beans.factory.ObjectProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope

// `ObjectProvider`가 `ObjectFactory`를 상속하고 있음
@Scope("singleton")
class ProviderCountClient(
    @Autowired
    private val provider: Provider<PrototypeCounter>,
    private val objectProvider: ObjectProvider<PrototypeCounter>,
    private val objectFactory: ObjectFactory<PrototypeCounter>
) {
    var value: Int = 0
        private set

    val providerCounter: PrototypeCounter
        get() = provider.get()

    val objectProviderCounter: PrototypeCounter
        get() = objectProvider.getObject()

    val objectFactoryCounter: PrototypeCounter
        get() = objectFactory.`object`

    fun increaseAndGetCounter(): PrototypeCounter {
        val counter = objectProvider.getObject()
        counter.increase()
        this.value = counter.value

        return counter
    }

    @PostConstruct
    fun init() = println("ProviderCountClient (init) : $this")

    @PreDestroy
    fun release() = println("ProviderCountClient (release) : $this")
}
