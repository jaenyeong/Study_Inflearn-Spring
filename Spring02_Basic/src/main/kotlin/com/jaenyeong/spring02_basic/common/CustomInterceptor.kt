package com.jaenyeong.spring02_basic.common

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

@Component
class CustomInterceptor(
    private val provider: ObjectProvider<CustomLogger>
) : HandlerInterceptor {

    // Request 요청이 처리되기 전에 호출되는 함수
    // 기본적으로 `true`를 반환하지만 상황에 따라 `false`를 반환하여 요청 처리를 중지시킬 수 있음
    // 사용자 인증 및 인가, 요청 파라미터의 검증, 요청 로깅 등에 활용될 수 있음
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val requestUrl = request.requestURL.toString()
        val logger = provider.getObject()

        logger.changeRequestUrl(requestUrl)
        logger.log("[Interceptor PreHandle]")

        return true
    }

    // 요청을 처리하는 핸들러 실행 후에 그리고 `DispatcherServlet`이 `View`를 렌더링하기 전에 호출되는 함수
    // `ModelAndView`에 속성을 추가하거나 요청 처리 시간을 계산할 때 사용할 수 있음
    // 뷰에서 사용되는 공통 모델 객체 데이터 추가, 처리 결과 로깅, 성능 모니터링 등에 활용될 수 있음
    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        val logger = provider.getObject()
        logger.log("[Interceptor PostHandle]")
    }

    // `DispatcherServlet`이 `View`를 렌더링한 후에 호출되는 함수
    // 외부 시스템 커넥션 및 파일 리소스 정리, 유저 활동 추적, 발생 예외 로깅 등에 활용될 수 있음
    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
        val logger = provider.getObject()
        logger.log("[Interceptor AfterCompletion]")
    }
}
