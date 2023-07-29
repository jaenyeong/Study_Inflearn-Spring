package com.jaenyeong.spring02_basic.beanscope.counter

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.context.annotation.Scope

@Scope("prototype")
class PrototypeCounter {
    var value: Int = 0
        private set

    fun increase() {
        value++
    }

    @PostConstruct
    fun init() = println("PrototypeCounter (init) : $this")

    @PreDestroy
    fun release() = println("PrototypeCounter (release) : $this")
}
