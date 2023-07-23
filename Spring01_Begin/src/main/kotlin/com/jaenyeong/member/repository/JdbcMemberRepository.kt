package com.jaenyeong.member.repository

import com.jaenyeong.member.domain.Member
import org.springframework.jdbc.datasource.DataSourceUtils
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import javax.sql.DataSource

class JdbcMemberRepository(
    private val dataSource: DataSource
) : MemberRepository {
    
    override fun save(member: Member): Member {
        val insertSql = "insert into member(name) values(?)"
        val generatedId = insertSql.insertExecuteAndGetKeyOrNull(listOf(member.name))
        member.id = generatedId ?: throw SQLException("ID 조회 실패")

        return member
    }

    override fun findById(id: Long): Member? {
        val sql = "select * from member where id = ?"

        return findMember(sql = sql, parameters = listOf(id))
    }

    private fun findMember(sql: String, parameters: List<Any>): Member? {
        return sql.selectExecute(parameters) { resultSet ->
            if (resultSet.next()) resultSet.toMember() else null
        }
    }

    override fun findByName(name: String): Member? {
        val sql = "select * from member where name = ?"

        return findMember(sql = sql, parameters = listOf(name))
    }

    override fun findAll(): List<Member> {
        return "select * from member".selectExecute { resultSet ->
            generateSequence { if (resultSet.next()) resultSet else null }
                .map { it.toMember() }
                .toList()
        }
    }

    override fun clear() {
        "truncate table member".truncateExecute()
    }

    private fun String.insertExecuteAndGetKeyOrNull(parameters: List<Any> = emptyList()): Long? {
        return this.execute(parameters, generatedKeys = true) { preparedStatement ->
            preparedStatement.executeUpdate()
            preparedStatement.generatedKeys.let { resultSet ->
                if (resultSet.next()) resultSet.getLong(1) else null
            }
        }
    }

    private fun <T> String.selectExecute(parameters: List<Any> = emptyList(), handler: (ResultSet) -> T): T {
        return this.execute(parameters) { preparedStatement ->
            preparedStatement.executeQuery().use(handler)
        }
    }

    private fun String.truncateExecute(parameters: List<Any> = emptyList()) {
        this.execute(parameters) { preparedStatement ->
            preparedStatement.executeUpdate()
        }
    }

    private fun <T> String.execute(
        parameters: List<Any> = emptyList(),
        generatedKeys: Boolean = false,
        handler: (PreparedStatement) -> T
    ): T {
        return dbAction { connection ->
            val returnGeneratedKeys = if (generatedKeys) Statement.RETURN_GENERATED_KEYS else Statement.NO_GENERATED_KEYS

            connection.prepareStatement(this, returnGeneratedKeys).apply {
                setParameters(parameters)
            }.let(handler)
        }
    }

    private fun ResultSet.toMember(): Member {
        return Member(
            id = this.getLong("id"),
            name = this.getString("name")
        )
    }

    private fun PreparedStatement.setParameters(parameters: List<Any>) {
        parameters.forEachIndexed { index, parameter ->
            when (parameter) {
                is String -> this.setString(index + 1, parameter)
                is Long -> this.setLong(index + 1, parameter)
            }
        }
    }

    private fun <T> dbAction(action: (Connection) -> T): T {
        // 스프링을 사용할 때 DataSourceUtils의 커넥션을 사용하는 것이 좋음
        // 스프링 트랜잭션 등에 영향을 받을 때 동일 커넥션을 유지해줌
        val connection = DataSourceUtils.getConnection(dataSource)

        return try {
            action(connection)
        } catch (e: Exception) {
            throw IllegalStateException(e)
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource)
        }
    }
}
