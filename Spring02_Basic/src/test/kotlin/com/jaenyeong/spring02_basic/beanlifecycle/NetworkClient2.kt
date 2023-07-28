package com.jaenyeong.spring02_basic.beanlifecycle

// 빈 등록 시 @Bean(initMethod = "init", destroyMethod = "release") 형태로 등록
// - `InitializingBean`, `DisposableBean`를 구현하는 NetworkClient1의 단점을 극복
class NetworkClient2(
    private var url: String
) {
    init {
        println("[NetworkClient2] Init url : $url")
    }

    private fun connect() {
        println("[NetworkClient2] connect : $url")
    }

    private fun send(message: String) {
        println("[NetworkClient2] call : $url, message : $message")
    }

    private fun disconnect() {
        println("[NetworkClient2] disconnect : $url")
    }

    fun changeUrl(url: String) {
        this.url = url
    }

    fun init() {
        println("---- [NetworkClient2 : init] ----")
        connect()
        send("Hello NetworkClient2")
    }

    fun release() {
        println("---- [NetworkClient2 : release] ----")
        disconnect()
    }
}
