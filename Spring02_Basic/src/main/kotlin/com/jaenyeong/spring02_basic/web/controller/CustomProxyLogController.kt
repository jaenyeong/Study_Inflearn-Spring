package com.jaenyeong.spring02_basic.web.controller

import com.jaenyeong.spring02_basic.common.CustomProxyLogger
import com.jaenyeong.spring02_basic.web.CustomProxyLogService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class CustomProxyLogController(
    private val logService: CustomProxyLogService,
    private val logger: CustomProxyLogger
) {
    @GetMapping("/custom-proxy-log")
    @ResponseBody
    fun customLog(request: HttpServletRequest): String {
        logger.log("[CustomProxyLogController]")

        logService.execute()

        return "OK"
    }
}
