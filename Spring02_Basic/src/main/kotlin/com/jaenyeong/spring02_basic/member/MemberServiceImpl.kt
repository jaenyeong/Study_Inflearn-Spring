package com.jaenyeong.spring02_basic.member

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
