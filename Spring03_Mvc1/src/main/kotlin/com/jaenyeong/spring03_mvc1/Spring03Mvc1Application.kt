package com.jaenyeong.spring03_mvc1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

// 서블릿 컴포넌트(filter, servlet, listener) 스캔 (스캔 동작은 임베디드 서버를 사용할 때만 실행됨)
@ServletComponentScan
@SpringBootApplication
class Spring03Mvc1Application

/*
IDEA에서 멀티 모듈 구조에서 특정 하위 모듈이 `webapp`을 `static` 패스로 인식되지 않음 (현재 이유는 찾지 못함)
- 새 프로젝트에서는 별도의 설정 없이 `webapp` 경로를 static 경로로 인식하는 것을 확인
- 하지만 해당 멀티 모듈에서는 의존 관계가 없는 새 모듈을 생성하여 실행해도 인식하지 못했고
  `application.yml` 설정 등으로 `static` 경로 클래스패스를 추가해보았으나 여전히 인식하지 못함

`webapp`을 `static` 경로로 인식할 수 있게 하려면 Gradle의 `bootRun`을 통해 실행
`IDEA`를 통해 main 함수를 `run` 명령으로 실행 시키면 인식하지 못하지만 `bootRun`로 실행시키면 찾음
 */
fun main(args: Array<String>) {
	runApplication<Spring03Mvc1Application>(*args)
}
