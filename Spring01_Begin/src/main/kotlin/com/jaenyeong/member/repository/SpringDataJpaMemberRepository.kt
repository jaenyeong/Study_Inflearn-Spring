package com.jaenyeong.member.repository

import com.jaenyeong.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface SpringDataJpaMemberRepository : JpaRepository<Member, Long>, MemberRepository {
    override fun findByName(name: String): Member?

    @Modifying
    @Transactional
    @Query(value = "truncate table member", nativeQuery = true)
    override fun clear()
}
