package com.jaenyeong.spring02_basic

import com.jaenyeong.spring02_basic.member.Grade.VIP
import com.jaenyeong.spring02_basic.member.Member
import com.jaenyeong.spring02_basic.member.MemberService
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class MemberApp

fun main(args: Array<String>) {
    val context = AnnotationConfigApplicationContext(AppConfig::class.java)

    val memberService = context.getBean("memberService", MemberService::class.java)

    val newMember = Member(id = 1L, name = "member1", grade = VIP)
    memberService.join(newMember)

    val foundMember = memberService.findMember(newMember.id)

    println("newMember = $newMember")
    println("foundMember = $foundMember")
}
