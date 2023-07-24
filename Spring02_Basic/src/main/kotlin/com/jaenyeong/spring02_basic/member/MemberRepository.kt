package com.jaenyeong.spring02_basic.member

interface MemberRepository {
    fun save(member: Member): Long
    fun findById(id: Long): Member?
}
