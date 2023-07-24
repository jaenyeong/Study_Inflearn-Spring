package com.jaenyeong.spring02_basic.member

import com.jaenyeong.spring02_basic.member.Grade.VIP
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MemberServiceTest : BehaviorSpec({

    Given("If given newMember") {
        val newMember = Member(id = 1L, name = "member1", grade = VIP)
        val memberService: MemberService = MemberServiceImpl()

        When("Attempting `join`") {
            memberService.join(newMember)

            Then("`join` is succeeded") {
                val foundMember = memberService.findMember(newMember.id)

                foundMember shouldBe newMember
            }
        }
    }
})
