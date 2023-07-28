package com.jaenyeong.spring02_basic.beanlifecycle

import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean

// `InitializingBean`, `DisposableBean`는 현재는 거의 사용되지 않음
// - 코드 자체가 스프링의 의존적일 수 밖에 없음
// - 수정할 수 없는 외부 라이브러리 등을 빈으로 등록한 경우는 적용하기 어려움
class NetworkClient1(
    private var url: String
) : InitializingBean, DisposableBean {
    init {
        println("[NetworkClient1] Init url : $url")
    }

    private fun connect() {
        println("[NetworkClient1] connect : $url")
    }

    private fun send(message: String) {
        println("[NetworkClient1] call : $url, message : $message")
    }

    private fun disconnect() {
        println("[NetworkClient1] disconnect : $url")
    }

    fun changeUrl(url: String) {
        this.url = url
    }

    // (InitializingBean) 의존성 주입이 끝난 후 초기화 작업
    override fun afterPropertiesSet() {
        println("---- [NetworkClient1 : afterPropertiesSet] ----")
        connect()
        send("Hello NetworkClient1")
    }

    // (DisposableBean) 빈이 소멸하기 전에 정리 작업
    override fun destroy() {
        println("---- [NetworkClient1 : destroy] ----")
        disconnect()
    }
}
