package com.jaenyeong.spring02_basic.web

import com.jaenyeong.spring02_basic.common.CustomProxyLogger
import org.springframework.stereotype.Service

@Service
class CustomProxyLogService(
    private val logger: CustomProxyLogger
) {
    fun execute() {
        logger.log("[CustomProxyLogService]")
    }
}
