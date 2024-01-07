package com.jaenyeong.spring03_mvc1.domain.member

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.containExactly
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

class MemberRepositoryTest : FunSpec({

    beforeEach {
        MemberRepository.clearStore()
    }

    test("Save test") {
        val newMember = Member(username = "Hello", age = 20)

        val savedMember = MemberRepository.save(newMember)

        val foundMember = MemberRepository.findById(savedMember.id)

        foundMember shouldBe savedMember
    }

    test("Find all test") {
        val firstMember = Member(username = "Hello1", age = 20)
        val secondMember = Member(username = "Hello2", age = 30)

        MemberRepository.save(firstMember)
        MemberRepository.save(secondMember)

        val members = MemberRepository.findAll()

        members.size shouldBe 2
        members should containExactly (listOf(firstMember, secondMember))
    }
})
