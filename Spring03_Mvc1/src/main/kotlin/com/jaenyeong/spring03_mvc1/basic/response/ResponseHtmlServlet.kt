package com.jaenyeong.spring03_mvc1.basic.response

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@WebServlet(name = "responseHtmlServlet", urlPatterns = ["/response-html"])
class ResponseHtmlServlet : HttpServlet() {
    override fun service(request: HttpServletRequest, response: HttpServletResponse) {
        response.contentType = "text/html"
        response.characterEncoding = "utf-8"

        response.writer.println("<html>")
        response.writer.println("<body>")
        response.writer.println("  <div>Hello?</div>")
        response.writer.println("</body>")
        response.writer.println("</html>")
    }
}
