package com.jaenyeong.spring02_basic

import com.jaenyeong.spring02_basic.member.Grade.VIP
import com.jaenyeong.spring02_basic.member.Member

class MemberApp

fun main(args: Array<String>) {
    val config = AppConfig()
    val memberService = config.memberService()

    val newMember = Member(id = 1L, name = "member1", grade = VIP)
    memberService.join(newMember)

    val foundMember = memberService.findMember(newMember.id)

    println("newMember = $newMember")
    println("foundMember = $foundMember")
}
