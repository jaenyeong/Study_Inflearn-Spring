package com.jaenyeong.spring03_mvc1.basic.request

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "requestParamServlet", urlPatterns = ["/request-param"])
class RequestParamServlet : HttpServlet() {
    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        printParams(request)

        response.writer.write("OK")
    }

    private fun printParams(request: HttpServletRequest) {
        println()
        println("--- REQUEST-Param Start ---")

        println("[전체 파라미터 조회]")
        request.parameterNames
            .asIterator()
            .forEachRemaining { paramName ->
                println("paramName : $paramName & paramValue : ${request.getParameter(paramName)}")
            }
        println()

        println("[이름이 같은 파라미터 조회]")
        request.getParameterValues("query")?.let {
            it.forEach { query ->
                println("query : $query")
            }
        }
        println()

        println("--- REQUEST-Param End ---")
        println()
    }
}
