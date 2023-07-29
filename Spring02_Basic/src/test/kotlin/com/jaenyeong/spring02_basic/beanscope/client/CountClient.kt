package com.jaenyeong.spring02_basic.beanscope.client

import com.jaenyeong.spring02_basic.beanscope.counter.PrototypeCounter
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.context.annotation.Scope

@Scope("singleton")
class CountClient(
    private val counter: PrototypeCounter
) {
    val value: Int
        get() = counter.value

    fun increase() {
        counter.increase()
    }

    @PostConstruct
    fun init() = println("CountClient (init) : $this")

    @PreDestroy
    fun release() = println("CountClient (release) : $this")
}
