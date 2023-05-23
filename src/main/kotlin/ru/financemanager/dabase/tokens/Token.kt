package ru.financemanager.dabase.tokens

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.financemanager.dabase.user.User

object Token : Table() {
    private val id = Token.varchar("id", 50)
    private val user_login = Token.varchar("user_login", 25)
    private val token = Token.varchar("token", 50)

    fun insert(tokenDTO: TokenDTO) {
        transaction {
            Token.insert {
                it[id] = tokenDTO.id
                it[user_login] = tokenDTO.login
                it[token] = tokenDTO.token
            }
        }
    }

    fun fetchTokens(): List<TokenDTO> {
        return try {
            transaction {
                Token.selectAll().toList()
                    .map {
                        TokenDTO(
                            id = it[Token.id],
                            token = it[token],
                            login = it[user_login]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun getToken(us_token: String): TokenDTO? {
        return try {
            transaction {
                val tokenModel = Token.select { Token.token.eq(us_token) }.single()
                TokenDTO(
                    id = tokenModel[Token.id],
                    token = tokenModel[token],
                    login = tokenModel[user_login]
                )
            }
        } catch (e: Exception) {
            null
        }
    }
}