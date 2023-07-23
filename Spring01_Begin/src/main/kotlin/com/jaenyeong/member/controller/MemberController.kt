package com.jaenyeong.member.controller

import com.jaenyeong.member.domain.Member
import com.jaenyeong.member.dto.MemberForm
import com.jaenyeong.member.service.MemberService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class MemberController(
    private val memberService: MemberService
) {
    @GetMapping("/members/new")
    fun createForm(): String {
        return "members/createMemberForm"
    }

    @PostMapping("/members/new")
    fun create(form: MemberForm): String {
        memberService.join(Member(name = form.name))

        return "redirect:/"
    }
}
