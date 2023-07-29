package com.jaenyeong.spring02_basic.web.controller

import com.jaenyeong.spring02_basic.common.CustomLogger
import com.jaenyeong.spring02_basic.web.CustomLogService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class CustomLogController(
    private val logService: CustomLogService,
    private val provider: ObjectProvider<CustomLogger>,
) {
    @GetMapping("/custom-log")
    @ResponseBody
    fun customLog(request: HttpServletRequest): String {
        val logger = provider.getObject()
        logger.log("[CustomLogController]")

        logService.execute()

        return "OK"
    }
}
