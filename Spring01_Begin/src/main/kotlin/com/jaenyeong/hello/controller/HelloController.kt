package com.jaenyeong.hello.controller

import com.jaenyeong.hello.dto.HelloData
import com.jaenyeong.hello.dto.HelloValue
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HelloController {

    @GetMapping("/hello")
    fun hello(model: Model): String {
        model.addAttribute("data", "Hello!")
        return "hello"
    }

    @GetMapping("/hello-mvc")
    fun helloMvc(@RequestParam("name") name: String?, model: Model): String {
        model.addAttribute("name", name)
        return "hello-template"
    }

    @GetMapping("/hello-string")
    @ResponseBody
    fun helloString(@RequestParam("name") name: String): String {
        return "Hello $name"
    }

    @GetMapping("/hello-api-dataclass")
    @ResponseBody
    fun helloApiWithDataClass(@RequestParam("name") name: String): HelloData {
        return HelloData(name)
    }

    @GetMapping("/hello-api-valueclass")
    @ResponseBody
    fun helloApiWithValueClass(@RequestParam("name") name: String): HelloValue {
        return HelloValue(name)
    }
}
