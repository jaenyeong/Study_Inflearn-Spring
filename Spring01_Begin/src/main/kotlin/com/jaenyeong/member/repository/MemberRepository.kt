package com.jaenyeong.member.repository

import com.jaenyeong.member.domain.Member

interface MemberRepository {
    fun save(member: Member): Member
    fun findById(id: Long): Member?
    fun findByName(name: String): Member?
    fun findAll(): List<Member>
    fun clear()
}
