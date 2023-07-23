package com.jaenyeong

import com.jaenyeong.member.repository.JdbcMemberRepository
import com.jaenyeong.member.repository.JdbcTemplateMemberRepository
import com.jaenyeong.member.repository.MemberRepository
import com.jaenyeong.member.service.MemberService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class SpringConfig(
    private val dataSource: DataSource
) {
    // 아래와 같이 빈을 등록할 수도 있음
    // 이미 등록(스캔)된 빈을 설정하면 에러가 발생함
    //// The bean 'memberService', defined in class path resource [com/jaenyeong/SpringConfig.class],
    //// could not be registered. A bean with that name has already been defined in file
    @Bean
    fun memberService(): MemberService {
        return MemberService(memberRepository())
    }

    @Bean
    fun memberRepository(): MemberRepository {
//        return MemoryMemberRepository()
//        return JdbcMemberRepository(dataSource)
        return JdbcTemplateMemberRepository(dataSource)
    }
}
