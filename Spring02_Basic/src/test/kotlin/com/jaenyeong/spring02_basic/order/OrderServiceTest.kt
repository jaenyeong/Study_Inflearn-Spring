package com.jaenyeong.spring02_basic.order

import com.jaenyeong.spring02_basic.Spring02BasicApplication
import com.jaenyeong.spring02_basic.member.Grade.VIP
import com.jaenyeong.spring02_basic.member.Member
import com.jaenyeong.spring02_basic.member.MemberService
import com.jaenyeong.spring02_basic.product.Product
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
class OrderServiceTest : BehaviorSpec() {
    override fun extensions(): List<Extension> = listOf(SpringExtension)

    @Autowired
    private lateinit var memberService: MemberService
    @Autowired
    private lateinit var orderService: OrderService

    init {

        Given("If given newMember") {
            val newMember = Member(id = 1L, name = "member1", grade = VIP)
            memberService.join(newMember)

            When("Creating `order`") {
                val product = Product(name = "Product1", price = 10_000)

                val createdOrder = orderService.createOrder(newMember.id, product = product)

                Then("`Created Order` is validate") {
                    createdOrder.memberId shouldBe newMember.id
                    createdOrder.productName shouldBe product.name
                    createdOrder.productPrice shouldBe product.price
                    createdOrder.discountPrice shouldBe 1_000
                }
            }
        }
    }
}
