package com.jaenyeong.member.repository

import com.jaenyeong.member.domain.Member
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import javax.sql.DataSource

class JdbcTemplateMemberRepository(dataSource: DataSource) : MemberRepository {
    private val jdbcTemplate: JdbcTemplate = JdbcTemplate(dataSource)

    override fun save(member: Member): Member {
        val jdbcInsert = SimpleJdbcInsert(jdbcTemplate)
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id")

        val parameters = HashMap<String, Any>()
        parameters["name"] = member.name

        val savedMemberId = jdbcInsert.executeAndReturnKey(MapSqlParameterSource(parameters))
        member.id = savedMemberId.toLong()

        return member
    }

    override fun findById(id: Long): Member? {
        return jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id)
            .find { member -> member.id == id }
    }

    override fun findByName(name: String): Member? {
        return jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name)
            .find { member -> member.name == name }
    }

    override fun findAll(): List<Member> {
        return jdbcTemplate.query("select * from member", memberRowMapper())
    }

    override fun clear() {
        jdbcTemplate.execute("truncate table member")
    }

    private fun memberRowMapper(): RowMapper<Member> {
        return RowMapper { resultSet, _ ->
            Member(
                id = resultSet.getLong("id"),
                name = resultSet.getString("name")
            )
        }
    }
}
