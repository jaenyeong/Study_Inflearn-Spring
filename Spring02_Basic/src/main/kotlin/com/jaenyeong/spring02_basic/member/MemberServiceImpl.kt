package com.jaenyeong.spring02_basic.member

import org.springframework.stereotype.Component

@Component
class MemberServiceImpl(
    val memberRepository: MemberRepository
) : MemberService {

    override fun join(member: Member) {
        memberRepository.save(member)
    }

    override fun findMember(memberId: Long): Member? {
        return memberRepository.findById(memberId)
    }
}
