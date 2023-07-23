package com.jaenyeong.member.service

import com.jaenyeong.member.domain.Member
import com.jaenyeong.member.repository.MemberRepository
import com.jaenyeong.member.repository.MemoryMemberRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MemberServiceTest : BehaviorSpec({
    // `beforeSpec`과 달리 Given, When, Then 함수 호출 사이에도 실행됨
//    beforeTest {}
//    beforeSpec {}

    Given("If given member`s name is unique") {
        val memberRepository: MemberRepository = MemoryMemberRepository()
        val memberService = MemberService(memberRepository)

        val newMember = Member(name = "Member1")

        When("Attempting `join`") {
            val savedId = memberService.join(newMember)

            Then("`join` is succeeded") {
                val foundMember = memberService.findMemberById(savedId)
                requireNotNull(foundMember)

                foundMember shouldBe newMember
            }
        }
    }

    Given("If given member`s name is duplicate name") {
        val memberRepository: MemberRepository = MemoryMemberRepository()
        val memberService = MemberService(memberRepository)

        val originMember = Member(name = "Member1")
        val newMember = Member(name = "Member1")

        memberService.join(originMember)

        When("Attempting `join`") {
            Then("exception should be thrown") {
                shouldThrow<IllegalArgumentException> {
                    memberService.join(newMember)
                }
            }
        }
    }
})
