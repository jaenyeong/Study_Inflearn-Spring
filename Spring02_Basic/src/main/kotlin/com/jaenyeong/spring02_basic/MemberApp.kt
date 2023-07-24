package com.jaenyeong.spring02_basic

import com.jaenyeong.spring02_basic.member.Grade.VIP
import com.jaenyeong.spring02_basic.member.Member
import com.jaenyeong.spring02_basic.member.MemberService
import com.jaenyeong.spring02_basic.member.MemberServiceImpl

class MemberApp

fun main(args: Array<String>) {
    val newMember = Member(id = 1L, name = "member1", grade = VIP)

    val memberService: MemberService = MemberServiceImpl()
    memberService.join(newMember)

    val foundMember = memberService.findMember(newMember.id)

    println("newMember = $newMember")
    println("foundMember = $foundMember")
}
