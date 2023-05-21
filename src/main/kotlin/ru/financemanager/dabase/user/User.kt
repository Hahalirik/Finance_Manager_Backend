package ru.financemanager.dabase.user

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object User : Table(){
    private val login = User.varchar("login", 25)
    private val password = User.varchar("password", 25)
    private val email = User.varchar("email", 25).nullable()

    fun insertUser(userDTO: UserDTO) {
        transaction {
            User.insert {
                it[login] = userDTO.login
                it[password] = userDTO.password
                it[email] = userDTO.email
            }
        }
    }

    fun getUser(login: String): UserDTO? {
        return try {
            transaction {
                val userModel = User.select { User.login.eq(login) }.single()
                UserDTO(
                    login = userModel[User.login],
                    password = userModel[password],
                    email = userModel[email]
                )
            }
        } catch (e: Exception) {
            null
        }
    }
}