package com.jaenyeong.spring02_basic.common

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS
import org.springframework.stereotype.Component
import java.util.UUID

@Component
@Scope(value = "request", proxyMode = TARGET_CLASS)
class CustomProxyLogger(
    private val uuid: String = UUID.randomUUID().toString()
) {
    private var requestUrl: String = ""

    fun changeRequestUrl(newUrl: String) = run { this.requestUrl = newUrl }

    fun log(message: String) {
        println("[UUID : ${uuid}] [REQ URL : ${requestUrl}] : $message")
    }

    @PostConstruct
    fun init() {
        println("[UUID : ${uuid}] [PostConstruct] : [Logger Instance: ${this}]")
    }

    @PreDestroy
    fun release() {
        println("[UUID : ${uuid}] [PreDestroy] : [Logger Instance: ${this}]")
    }
}
