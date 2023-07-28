package com.jaenyeong.spring02_basic.autowired.notrequired

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.lang.Nullable
import org.springframework.stereotype.Component

@Component
class NotRequiredComponent {
    @Autowired(required = false)
    fun injectRequiredFalse(pojo: NotRequiredPojo?) {
        println("[injectRequiredFalse] : $pojo")
    }

    // `required = false` 설정하지 않더라도 파라미터가 nullable 타입이라면 테스트 코드 컴파일됨
    // 위 함수와의 차이는 `println` 명령의 실행 여부
    @Autowired
    fun injectRequiredTrue(pojo: NotRequiredPojo?) {
        println("[injectRequiredTrue] : $pojo")
    }

    // 코틀린은 Java와 다르게 @Nullable 애너테이션을 사용하더라도
    // 파라미터 자체가 `NotNull` 타입이라면 에러가 발생하여 위 함수랑 큰 차이 없음
    @Autowired
    fun injectWithNullableAnnotation(@Nullable pojo: NotRequiredPojo?) {
        println("[injectWithNullableAnnotation] : $pojo")
    }

    // Not Bean
    class NotRequiredPojo
}
