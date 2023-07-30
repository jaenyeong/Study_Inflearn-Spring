package com.jaenyeong.spring03_mvc1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

// 서블릿 컴포넌트(filter, servlet, listener) 스캔 (스캔 동작은 임베디드 서버를 사용할 때만 실행됨)
@ServletComponentScan
@SpringBootApplication
class Main

fun main(args: Array<String>) {
	runApplication<Main>(*args)
}
