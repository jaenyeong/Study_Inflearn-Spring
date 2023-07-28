package com.jaenyeong.spring02_basic.beanlifecycle

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy

// `@PostConstruct`, `@PreDestroy` 애너테이션을 태깅
// 사용이 간편하나 이 또한 외부 라이브러리의 클래스 등에는 적용할 수 없다는 단점이 있음
class NetworkClient3(
    private var url: String
) {
    init {
        println("[NetworkClient3] Init url : $url")
    }

    private fun connect() {
        println("[NetworkClient3] connect : $url")
    }

    private fun send(message: String) {
        println("[NetworkClient3] call : $url, message : $message")
    }

    private fun disconnect() {
        println("[NetworkClient3] disconnect : $url")
    }

    fun changeUrl(url: String) {
        this.url = url
    }

    @PostConstruct
    fun init() {
        println("---- [NetworkClient3 : PostConstruct] ----")
        connect()
        send("Hello NetworkClient3")
    }

    @PreDestroy
    fun release() {
        println("---- [NetworkClient3 : PreDestroy] ----")
        disconnect()
    }
}
