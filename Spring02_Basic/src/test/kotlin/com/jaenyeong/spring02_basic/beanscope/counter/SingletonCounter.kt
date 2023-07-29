package com.jaenyeong.spring02_basic.beanscope.counter

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.context.annotation.Scope

@Scope("singleton")
class SingletonCounter {
    var value: Int = 0
        private set

    fun increase() {
        value++
    }

    @PostConstruct
    fun init() = println("SingletonCounter (init) : $this")

    @PreDestroy
    fun release() = println("SingletonCounter (release) : $this")
}
