package com.jaenyeong.spring03_mvc1.basic.response

import com.fasterxml.jackson.databind.ObjectMapper
import com.jaenyeong.spring03_mvc1.basic.HelloData
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "responseJsonServlet", urlPatterns = ["/response-json"])
class ResponseJsonServlet(
    private val mapper: ObjectMapper
) : HttpServlet() {
    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        response.contentType = "application/json"
        response.characterEncoding = "utf-8"

        val helloData = HelloData(username = "Kim", age = 99)
        val result = mapper.writeValueAsString(helloData)

        response.writer.write(result)
    }
}
