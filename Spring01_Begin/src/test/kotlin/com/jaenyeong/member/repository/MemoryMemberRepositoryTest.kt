package com.jaenyeong.member.repository

import com.jaenyeong.member.domain.Member
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.ints.shouldNotBeGreaterThan
import io.kotest.matchers.ints.shouldNotBeLessThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

internal class MemoryMemberRepositoryTest : AnnotationSpec() {
    private val memberStore: MemoryMemberRepository = MemoryMemberRepository()

    @AfterEach
    fun clearStore() {
        memberStore.clear()
    }

    @Test
    fun save() {
        val newMember = Member(name = "Member1")
        memberStore.save(newMember)

        val foundMember = memberStore.findById(newMember.id)
        foundMember shouldBe newMember
    }

    @Test
    fun findByName() {
        val member1 = Member(name = "Member1")
        memberStore.save(member1)

        val member2 = Member(name = "Member2")
        memberStore.save(member2)

        val foundMember2 = memberStore.findByName(name = member2.name)

        foundMember2 shouldNotBe member1
        foundMember2 shouldBe member2
    }

    @Test
    fun findAll() {
        val member1 = Member(name = "Member1")
        memberStore.save(member1)

        val member2 = Member(name = "Member2")
        memberStore.save(member2)

        val foundMembers = memberStore.findAll()

        foundMembers.size shouldBe 2

        foundMembers.size shouldNotBeLessThan 2
        foundMembers.size shouldNotBeGreaterThan 2
    }
}
