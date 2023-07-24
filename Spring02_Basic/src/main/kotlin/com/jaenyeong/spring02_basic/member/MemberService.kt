package com.jaenyeong.spring02_basic.member

interface MemberService {
    fun join(member: Member)
    fun findMember(memberId: Long): Member?
}
