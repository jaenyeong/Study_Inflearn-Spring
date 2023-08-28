package com.jaenyeong.spring03_mvc1.basic.request

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.util.Enumeration

@WebServlet(name = "requestHeaderServlet", urlPatterns = ["/request-header"])
class RequestHeaderServlet : HttpServlet() {
    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        printStartLine(request)
        printHeaders(request)
        printHeaderUtils(request)
        printEtc(request)

        response.writer.write("OK")
    }

    private fun printStartLine(request: HttpServletRequest) {
        println()
        println("--- REQUEST-Line Start ---")

        println("request.method : ${request.method}")
        println("request.protocol : ${request.protocol}")
        println("request.scheme : ${request.scheme}")
        println()

        println("request.requestURI : ${request.requestURI}")
        println("request.requestURL : ${request.requestURL}")
        println()

        println("request.queryString : ${request.queryString}")
        println("request.isSecure : ${request.isSecure}")
        println("--- REQUEST-Line End ---")
        println()
    }

    private fun printHeaders(request: HttpServletRequest) {
        println("--- REQUEST-Headers Start ---")

        // 기존 방식
        val headerNames: Enumeration<String> = request.headerNames
        while (headerNames.hasMoreElements()) {
            val headerName = headerNames.nextElement()
            println("HeaderName : $headerName")
        }

        println()

        // 새로운 방식
        request.headerNames
            .asIterator()
            .forEachRemaining { headerName -> println("HeaderName : $headerName") }

        println("--- REQUEST-Headers End ---")
    }

    private fun printHeaderUtils(request: HttpServletRequest) {
        println()
        println("--- Header 편의 조회 Start ---")

        println("[Host 조회]")
        println("request.serverName : ${request.serverName}")
        println("request.serverPort : ${request.serverPort}")
        println()

        println("[Accept-Language 조회]")
        println("request.locale : ${request.locale}")
        request.locales
            .asIterator()
            .forEachRemaining { locale -> println("locale : $locale") }
        println()

        println("[Cookie 조회]")
        request.cookies?.let {
            it.forEach { cookie ->
                println("cookie.name : ${cookie.name} & cookie.value : ${cookie.value}")
            }
        }
        println()

        println("[Content 조회]")
        println("request.contentType : ${request.contentType}")
        println("request.contentLength : ${request.contentLength}")
        println("request.characterEncoding : ${request.characterEncoding}")

        println("--- Header 편의 조회 End ---")
        println()
    }

    private fun printEtc(request: HttpServletRequest) {
        println()
        println("--- 기타 조회 Start ---")

        println("[Remote 정보 조회]")
        println("request.remoteHost : ${request.remoteHost}")
        println("request.remoteAddr : ${request.remoteAddr}")
        println("request.remotePort : ${request.remotePort}")
        println()

        println("[Local 정보 조회]")
        println("request.localName : ${request.localName}")
        println("request.localAddr : ${request.localAddr}")
        println("request.localPort : ${request.localPort}")

        println("--- 기타 조회 End ---")
        println()
    }
}
