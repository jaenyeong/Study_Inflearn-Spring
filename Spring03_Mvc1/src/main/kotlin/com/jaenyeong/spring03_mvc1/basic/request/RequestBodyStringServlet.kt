package com.jaenyeong.spring03_mvc1.basic.request

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.util.StreamUtils
import java.nio.charset.StandardCharsets

@WebServlet(name = "requestBodyStringServlet", urlPatterns = ["/request-body-string"])
class RequestBodyStringServlet : HttpServlet() {
    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        println()
        val inputStream = request.inputStream!!
        val messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)

        println("messageBody : $messageBody")

        response.writer.write("OK")
    }
}
