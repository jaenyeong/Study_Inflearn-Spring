package com.jaenyeong.spring03_mvc1.basic.response

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "responseHeaderServlet", urlPatterns = ["/response-header"])
class ResponseHeaderServlet : HttpServlet() {
    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        // status-line
        response.status = HttpServletResponse.SC_OK

        // response-headers
        response.setHeader("Content-Type", "text/plain;charset=utf-8")
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate")
        response.setHeader("Pragma", "no-cache")
        response.setHeader("my-header", "hello!")

//        content(response)
//        cookie(response)
//        redirect(response)

        response.writer.println("OK")
    }

    private fun content(response: HttpServletResponse) {
//        response.setHeader("Content-Type", "text/plain;charset=utf-8")
        response.setHeader("Content-Type", "text/plain")
        response.characterEncoding = "utf-8"

        // 생략 시 자동 입력됨
//        response.setContentLength(1)
    }

    private fun cookie(response: HttpServletResponse) {
//        response.setHeader("Set-Cookie", "myCookie=Good; Max-Age=600")
        val cookie = Cookie("myCookie", "Good")
        cookie.maxAge = 600

        response.addCookie(cookie)
    }

    private fun redirect(response: HttpServletResponse) {
//        response.status = HttpServletResponse.SC_FOUND
//        response.setHeader("Location", "/basic/hello-form.html")
        response.sendRedirect("/basic/hello-form.html")
    }
}
