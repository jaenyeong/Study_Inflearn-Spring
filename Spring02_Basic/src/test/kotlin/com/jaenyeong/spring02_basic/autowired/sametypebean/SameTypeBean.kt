package com.jaenyeong.spring02_basic.autowired.sametypebean

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
// @Autowired 매칭 순서
// 1. 타입
// 2. 같은 타입이 여러개인 경우, `@Primary` 컴포넌트 > `@Qualifier` 지정 > 필드(파라미터)명 순
//    `@Primary`와 `@Qualifier`가 함께 사용된다면 `@Primary`가 우선순위가 높음
class SameTypeBean(
    val subNotPrimaryBean: SubNotPrimaryBean,
    val superPrimaryBean: SuperPrimaryBean,
    val subBeanA: SuperBean,
    @Qualifier("subBeanB") val superBean: SuperBean,
)
