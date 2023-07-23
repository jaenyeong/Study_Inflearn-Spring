package com.jaenyeong.member.service

import com.jaenyeong.member.domain.Member
import com.jaenyeong.member.repository.MemberRepository

class MemberService(
    private val memberRepository: MemberRepository
) {
    fun join(member: Member): Long {
        assertUniqueMemberName(member.name)

        return memberRepository.save(member).id
    }

    private fun assertUniqueMemberName(givenName: String) {
        memberRepository.findByName(givenName)?.let {
            throw IllegalArgumentException("Member already exists: $givenName")
        }
    }

    fun findMembers(): List<Member> {
        return memberRepository.findAll()
    }

    fun findMemberById(id: Long): Member? {
        return memberRepository.findById(id)
    }
}
