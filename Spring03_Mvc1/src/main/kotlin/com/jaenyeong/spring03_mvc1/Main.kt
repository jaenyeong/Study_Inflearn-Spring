package com.jaenyeong.spring03_mvc1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

// 서블릿 컴포넌트(filter, servlet, listener) 스캔 (스캔 동작은 임베디드 서버를 사용할 때만 실행됨)
@ServletComponentScan
@SpringBootApplication
class Main

// IDEA에 gradle의 설정이 `IDEA`가 아닌 `Gradle(Default)`로 되어 있는 상태에서
// main 함수를 `IDEA`를 통해 run 명령을 통해 그냥 실행 시키면 `webapp`의 `index` 페이지를 찾지 못하나
// Gradle의 `bootRun`을 통해 실행시킬 경우 `webapp`의 `index`를 찾음
fun main(args: Array<String>) {
	runApplication<Main>(*args)
}
