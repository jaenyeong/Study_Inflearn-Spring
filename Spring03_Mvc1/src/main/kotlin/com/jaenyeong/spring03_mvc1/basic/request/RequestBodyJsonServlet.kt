package com.jaenyeong.spring03_mvc1.basic.request

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.jaenyeong.spring03_mvc1.basic.HelloData
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.util.StreamUtils
import java.lang.Exception
import java.nio.charset.StandardCharsets

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = ["/request-body-json"])
class RequestBodyJsonServlet(
    private val mapper: ObjectMapper
) : HttpServlet() {
    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        println()
        val inputStream = request.inputStream!!
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)
        println("messageBody : $messageBody")
        println()

        try {
            val helloData = mapper.readValue<HelloData>(messageBody)
            println("helloData : $helloData")
            println()
        } catch (e: Exception) {
            println("HelloData parsing Error")
        }

        response.writer.write("OK")
    }
}
