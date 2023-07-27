package com.jaenyeong.spring02_basic.member

import org.springframework.stereotype.Component

@Component
class MemoryMemberRepository : MemberRepository {
    private val memberStore: MutableMap<Long, Member> = HashMap()
    private var sequence: Long = 0L

    override fun save(member: Member): Long {
        member.id = ++sequence
        memberStore[member.id] = member

        return member.id
    }

    override fun findById(id: Long): Member? {
        return memberStore[id]
    }
}
