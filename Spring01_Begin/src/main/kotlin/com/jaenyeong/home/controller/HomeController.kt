package com.jaenyeong.home.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {
    // 서블릿 컨테이너가 해당 URL을 먼저 매핑하여 `home.html`을 `index.html`보다 먼저 반환하게 됨
    @GetMapping("/")
    fun home(): String {
        return "home"
    }
}
