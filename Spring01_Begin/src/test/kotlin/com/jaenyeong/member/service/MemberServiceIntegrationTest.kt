package com.jaenyeong.member.service

import com.jaenyeong.Spring01BeginApplication
import com.jaenyeong.member.domain.Member
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(
    classes = [Spring01BeginApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Transactional
class MemberServiceIntegrationTest(
    private val memberService: MemberService
) : StringSpec() {

    // 테스트용 데이터가 DB에 커밋되지 않게 설정 (DB 데이터 롤백을 위해 추가)
    // 기본적으로 Kotest는 SpringBootTest의 동작을 알 수 없기 때문에 @Transactional만으로는 데이터가 롤백 되지 않음
    // SpringExtension은 TestContextManager를 통해 테스트 함수 전후로 Spring Test에 관련된 동작을 수행함
    override fun extensions(): List<Extension> = listOf(SpringExtension)

    init {
        "If given member`s name is unique, Attempting `join` is succeeded" {
            val newMember = Member(name = "Member777")

            val savedId = memberService.join(newMember)

            val foundMember = memberService.findMemberById(savedId)
            requireNotNull(foundMember)

            foundMember shouldBe newMember
        }

        "If given member`s name is duplicate, Attempting `join` is failed" {
            val originMember = Member(name = "Member123")
            val newMember = Member(name = "Member123")

            memberService.join(originMember)

            shouldThrow<IllegalArgumentException> {
                memberService.join(newMember)
            }
        }
    }
}
