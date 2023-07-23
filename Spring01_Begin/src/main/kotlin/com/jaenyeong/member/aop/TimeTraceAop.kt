package com.jaenyeong.member.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import java.lang.System.currentTimeMillis
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

@Aspect
@Component
class TimeTraceAop {

    @Around("execution(* com.jaenyeong..*(..))")
    fun execute(joinPoint: ProceedingJoinPoint): Any? {
        val start = currentTimeMillis()
        val currentDateTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(start), ZoneId.systemDefault())

        println("START : $joinPoint $currentDateTime")

        try {
            return joinPoint.proceed()
        } finally {
            val end = currentTimeMillis()
            val time = end - start
            println("End : $joinPoint ${time}ms")
        }
    }
}
