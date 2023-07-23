package com.jaenyeong.member.repository

import com.jaenyeong.member.domain.Member
import org.springframework.stereotype.Repository

@Repository
class MemoryMemberRepository : MemberRepository {
    private val memberStore: MutableMap<Long, Member> = HashMap()
    private var sequence: Long = 0L

    override fun save(member: Member): Member {
        member.id = ++sequence
        memberStore[member.id] = member
        return member
    }

    override fun findById(id: Long): Member? {
        return memberStore[id]
    }

    override fun findByName(name: String): Member? {
        return memberStore.values
            .find { member -> member.name == name }
    }

    override fun findAll(): List<Member> {
        return memberStore.values.map { it.copy() }
    }

    override fun clear() {
        memberStore.clear()
        sequence = 0
    }
}
