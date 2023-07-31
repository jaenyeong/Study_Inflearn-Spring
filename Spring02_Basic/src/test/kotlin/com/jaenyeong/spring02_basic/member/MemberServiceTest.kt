package com.jaenyeong.spring02_basic.member

import com.jaenyeong.spring02_basic.Spring02BasicApplication
import com.jaenyeong.spring02_basic.member.Grade.VIP
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(
    classes = [Spring02BasicApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Transactional
class MemberServiceTest : BehaviorSpec() {
    override fun extensions(): List<Extension> = listOf(SpringExtension)

    @Autowired
    private lateinit var memberService: MemberService

    init {
        Given("If given newMember") {
            val newMember = Member(id = 1L, name = "member1", grade = VIP)

            When("Attempting `join`") {
                memberService.join(newMember)

                Then("`join` is succeeded") {
                    val foundMember = memberService.findMember(newMember.id)

                    foundMember shouldBe newMember
                }
            }
        }
    }
}
