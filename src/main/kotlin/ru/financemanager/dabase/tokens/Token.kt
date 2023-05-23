package ru.financemanager.dabase.token

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

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
}