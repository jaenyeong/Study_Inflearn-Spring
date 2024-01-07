package com.jaenyeong.spring03_mvc1.domain.member

object MemberRepository {
    private val store: MutableMap<Long, Member> = mutableMapOf()
    private var sequence: Long = 0L

    fun save(member: Member): Member {
        member.changeId(++sequence)
        store[member.id] = member

        return member
    }

    fun findById(id: Long): Member? {
        return store[id]
    }

    fun findAll(): List<Member> {
        return store.values.toList()
    }

    fun clearStore() {
        store.clear()
    }
}
