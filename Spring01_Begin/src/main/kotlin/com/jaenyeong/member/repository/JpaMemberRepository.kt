package com.jaenyeong.member.repository

import com.jaenyeong.member.domain.Member
import jakarta.persistence.EntityManager

class JpaMemberRepository(
    private val entityManager: EntityManager
) : MemberRepository {

    override fun save(member: Member): Member {
        entityManager.persist(member)
        return member
    }

    override fun findById(id: Long): Member? {
        return entityManager.find(Member::class.java, id)
    }

    override fun findByName(name: String): Member? {
        val foundMembers = entityManager.createQuery("select m from Member m where m.name = :name", Member::class.java)
            .setParameter("name", name)
            .resultList

        return if (foundMembers.isNotEmpty()) foundMembers[0] else null
    }

    override fun findAll(): List<Member> {
        return entityManager.createQuery("select m from Member m", Member::class.java)
            .resultList
    }

    override fun clear() {
        TODO("Not yet implemented")
    }
}
