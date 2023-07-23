package com.jaenyeong.member.controller

import com.jaenyeong.member.service.MemberService
import org.springframework.stereotype.Controller

@Controller
class MemberController(
    private val memberService: MemberService
)
