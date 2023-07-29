package com.jaenyeong.spring02_basic.common

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

@Component
class CustomProxyInterceptor(
    private val logger: CustomProxyLogger
) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val requestUrl = request.requestURL.toString()

        logger.changeRequestUrl(requestUrl)
        logger.log("[CustomInterceptor PreHandle]")

        return true
    }

    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        logger.log("[CustomInterceptor PostHandle]")
    }

    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
        logger.log("[CustomInterceptor AfterCompletion]")
    }
}
