package com.jaenyeong.spring02_basic.web

import com.jaenyeong.spring02_basic.common.CustomLogger
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Service

@Service
class CustomLogService(
    private val provider: ObjectProvider<CustomLogger>
) {
    fun execute() {
        val logger = provider.getObject()
        logger.log("[CustomLogService]")
    }
}
