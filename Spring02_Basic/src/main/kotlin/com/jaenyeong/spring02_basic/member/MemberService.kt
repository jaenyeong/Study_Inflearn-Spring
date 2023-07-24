package com.jaenyeong.spring02_basic.member

import org.springframework.stereotype.Service

@Service
interface MemberService {
    fun join(member: Member)
    fun findMember(memberId: Long): Member?
}
